package sg.darren.ms.vb.account.model.http.accountInquiry;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AccountInquiryRequest {

    @NotEmpty
    String accountNo;

    @NotEmpty
    String status;

}
