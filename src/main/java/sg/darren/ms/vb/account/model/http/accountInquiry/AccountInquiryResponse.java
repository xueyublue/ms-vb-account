package sg.darren.ms.vb.account.model.http.accountInquiry;

import lombok.Builder;
import lombok.Data;
import sg.darren.ms.vb.account.model.entity.enums.AccountStatusEnum;

import java.util.Date;

@Data
@Builder
public class AccountInquiryResponse {

    String accountNo;

    private String accountName;

    private AccountStatusEnum status;

    private Date createdOn;

    private String createdBy;

    private Date updatedOn;

    private String updatedBy;
}
