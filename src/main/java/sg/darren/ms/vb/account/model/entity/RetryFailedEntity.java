package sg.darren.ms.vb.account.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_retry_failed")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RetryFailedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String uniqueReferenceNo;

    String accountNo;

    String currency;

    String creditDebitIndicator;

    BigDecimal amount;

    @Column(name = "created_on", updatable = false)
    private Date createdOn;

}
