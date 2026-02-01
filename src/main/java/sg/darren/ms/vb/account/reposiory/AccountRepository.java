package sg.darren.ms.vb.account.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.darren.ms.vb.account.model.entity.AccountEntity;
import sg.darren.ms.vb.account.model.entity.enums.AccountStatusEnum;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByAccountNo(String accountNo);

    AccountEntity findByAccountNoAndStatus(String accountNo, AccountStatusEnum status);

}
