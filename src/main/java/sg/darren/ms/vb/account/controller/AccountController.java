package sg.darren.ms.vb.account.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sg.darren.ms.vb.account.model.http.accountPosting.AccountPostingRequest;
import sg.darren.ms.vb.account.model.http.accountPosting.AccountPostingResponse;
import sg.darren.ms.vb.account.service.AccountPostingService;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountPostingService accountPostingService;

    @PostMapping(path = "/accout-posting")
    public AccountPostingResponse accountPosting(@RequestBody AccountPostingRequest request) {
        return accountPostingService.posting(request);
    }
}
