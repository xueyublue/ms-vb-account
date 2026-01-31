package sg.darren.ms.vb.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sg.darren.ms.vb.account.exception.DataNotFoundException;
import sg.darren.ms.vb.account.model.entity.AccountBalanceEntity;
import sg.darren.ms.vb.account.model.entity.AccountEntity;
import sg.darren.ms.vb.account.model.entity.enums.AccountStatusEnum;
import sg.darren.ms.vb.account.model.http.accountPosting.AccountPostingRequest;
import sg.darren.ms.vb.account.model.http.accountPosting.AccountPostingResponse;
import sg.darren.ms.vb.account.reposiory.AccountBalanceRepository;
import sg.darren.ms.vb.account.reposiory.AccountRepository;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountPostingService {

    private final AccountRepository accountRepository;
    private final AccountBalanceRepository accountBalanceRepository;

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
                    .balance(request.getCreditDebitIndicator().equalsIgnoreCase("C") ? request.getAmount() : request.getAmount().multiply(BigDecimal.valueOf(-1)))
                    .version(Long.valueOf("1"))
                    .build();
            accountBalanceRepository.save(accountBalance);
        } else {
            BigDecimal newBalance = request.getCreditDebitIndicator().equalsIgnoreCase("C") ? accountBalance.getBalance().add(request.getAmount()) : accountBalance.getBalance().subtract(request.getAmount());
            accountBalance.setBalance(newBalance);
            accountBalanceRepository.save(accountBalance);
        }
        return AccountPostingResponse.builder()
                .uniqueReferenceNo(request.getUniqueReferenceNo())
                .accountNo(request.getAccountNo())
                .balance(accountBalance.getBalance())
                .build();
    }

}
