package sg.darren.ms.vb.account.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_account")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
