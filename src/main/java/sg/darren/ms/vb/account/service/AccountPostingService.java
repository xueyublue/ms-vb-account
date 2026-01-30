package sg.darren.ms.vb.account.service;

import org.springframework.stereotype.Service;
import sg.darren.ms.vb.account.model.http.accountPosting.AccountPostingRequest;
import sg.darren.ms.vb.account.model.http.accountPosting.AccountPostingResponse;

@Service
public class AccountPostingService {

    public AccountPostingResponse posting(AccountPostingRequest request) {
        return new AccountPostingResponse();
    }

}
