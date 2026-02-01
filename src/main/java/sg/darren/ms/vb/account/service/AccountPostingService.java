package sg.darren.ms.vb.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import sg.darren.ms.vb.account.exception.ApplicationException;
import sg.darren.ms.vb.account.exception.DataNotFoundException;
import sg.darren.ms.vb.account.model.entity.AccountBalanceEntity;
import sg.darren.ms.vb.account.model.entity.AccountEntity;
import sg.darren.ms.vb.account.model.entity.RetryFailedEntity;
import sg.darren.ms.vb.account.model.entity.enums.AccountStatusEnum;
import sg.darren.ms.vb.account.model.http.accountPosting.AccountPostingRequest;
import sg.darren.ms.vb.account.model.http.accountPosting.AccountPostingResponse;
import sg.darren.ms.vb.account.reposiory.AccountBalanceRepository;
import sg.darren.ms.vb.account.reposiory.AccountRepository;
import sg.darren.ms.vb.account.reposiory.RetryFailedRepository;

import java.math.BigDecimal;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountPostingService {

    private final AccountRepository accountRepository;
    private final AccountBalanceRepository accountBalanceRepository;
    private final RetryFailedRepository retryFailedRepository;

    @Retryable(retryFor = {
            ObjectOptimisticLockingFailureException.class,  // for balance update failed when version not matched
            DataIntegrityViolationException.class   // for balance insert failed when same account number and currency exists
    },
            maxAttemptsExpression = "100",
            backoff = @Backoff(
                    delayExpression = "100",
                    maxDelayExpression = "1000",
                    multiplierExpression = "1.1",
                    random = false
            )
    )
    public AccountPostingResponse posting(AccountPostingRequest request) {
        // validate account
        AccountEntity account = accountRepository.findByAccountNo(request.getAccountNo());
        if (account == null) {
            throw DataNotFoundException.accountNumberNotFound(request.getAccountNo());
        }
        if (account.getAccountStatus() != AccountStatusEnum.ACTIVE) {
            throw DataNotFoundException.accountNotActive(request.getAccountNo());
        }
        // validate balance
        AccountBalanceEntity accountBalance = accountBalanceRepository.findByAccountNoAndCurrency(request.getAccountNo(), request.getCurrency());
        if (accountBalance == null) {
            accountBalance = AccountBalanceEntity.builder()
                    .accountNo(request.getAccountNo())
                    .currency(request.getCurrency())
                    .balance(request.isCredit() ? request.getAmount() : request.getAmount().multiply(BigDecimal.valueOf(-1)))
                    .createdOn(new Date())
                    .version(Long.valueOf("1"))
                    .build();
            accountBalanceRepository.save(accountBalance);
        } else {
            BigDecimal newBalance = request.isCredit() ? accountBalance.getBalance().add(request.getAmount()) : accountBalance.getBalance().subtract(request.getAmount());
            accountBalance.setBalance(newBalance);
            accountBalance.setUpdatedOn(new Date());
            accountBalanceRepository.save(accountBalance);
        }
        return AccountPostingResponse.builder()
                .uniqueReferenceNo(request.getUniqueReferenceNo())
                .accountNo(request.getAccountNo())
                .balance(accountBalance.getBalance())
                .build();
    }

    @Recover
    public AccountPostingResponse recover(Exception ex, AccountPostingRequest request) throws Exception {
        log.error("[recover] Failed to update account balance, accountNo={}, uniqueReferenceNo={}, error={}", request.getAccountNo(), request.getUniqueReferenceNo(), ex.getMessage());
        RetryFailedEntity retryFailedEntity = RetryFailedEntity.builder()
                .accountNo(request.getAccountNo())
                .currency(request.getCurrency())
                .uniqueReferenceNo(request.getUniqueReferenceNo())
                .creditDebitIndicator(request.getCreditDebitIndicator())
                .amount(request.getAmount())
                .createdOn(new Date())
                .build();
        retryFailedRepository.save(retryFailedEntity);
        throw ex;
    }

}
