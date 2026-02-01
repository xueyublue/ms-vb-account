package sg.darren.ms.vb.account.model.entity;

import jakarta.persistence.*;
import lombok.*;
import sg.darren.ms.vb.account.model.entity.enums.AccountStatusEnum;

import java.util.Date;

@Entity
@Table(name = "tbl_account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"accountNo"})
        },
        indexes = {
                @Index(name = "idx_accountNoStatus", columnList = "accountNo,status")
        }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNo;

    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatusEnum status;

    @Column(name = "created_on", updatable = false)
    private Date createdOn;

    private String createdBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    private String updatedBy;
}
