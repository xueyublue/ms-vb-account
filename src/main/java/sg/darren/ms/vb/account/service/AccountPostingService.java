package sg.darren.ms.vb.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sg.darren.ms.vb.account.exception.DataNotFoundException;
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
        AccountEntity account = accountRepository.findByAccountNo(request.getAccountNo());
        if (account == null) {
            throw DataNotFoundException.accountNumberNotFound(request.getAccountNo());
        }
        if (account.getAccountStatus() != AccountStatusEnum.ACTIVE) {
            throw DataNotFoundException.accountNotActive(request.getAccountNo());
        }
        return AccountPostingResponse.builder()
                .uniqueReferenceNo(request.getUniqueReferenceNo())
                .accountNo(request.getAccountNo())
                .balance(BigDecimal.ZERO)
                .build();
    }

}
