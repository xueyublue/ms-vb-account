package sg.darren.ms.vb.account.model.http.accountPosting;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountPostingRequest {

    String uniqueReferenceNo;

    String accountNo;

    String currency;

    String creditDebitIndicator;

    BigDecimal amount;

}
