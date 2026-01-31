package sg.darren.ms.vb.account.model.http.accountPosting;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountPostingResponse {

    String uniqueReferenceNo;

    String accountNo;

    BigDecimal balance;

}
