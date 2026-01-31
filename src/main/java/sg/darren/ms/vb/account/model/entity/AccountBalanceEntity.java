package sg.darren.ms.vb.account.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_account_balance")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNo;

    private String currency;

    private String totalDebit;

    private String totalCredit;

    private BigDecimal balance;

    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    private String createdBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    private String updatedBy;

    @Version
    private Long version;
}
