package sg.darren.ms.vb.account.model.http.accountPosting;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountPostingRequest {

    @NotEmpty
    String uniqueReferenceNo;

    @NotEmpty
    String accountNo;

    @NotEmpty
    @Size(min = 3, max = 3)
    String currency;

    @NotEmpty
    @Size(min = 1, max = 1)
    String creditDebitIndicator;

    @NotNull
    BigDecimal amount;

    public boolean isCredit() {
        return "C".equalsIgnoreCase(creditDebitIndicator);
    }

    public boolean isDebit() {
        return "D".equalsIgnoreCase(creditDebitIndicator);
    }

}
