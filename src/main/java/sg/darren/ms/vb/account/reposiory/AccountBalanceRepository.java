package sg.darren.ms.vb.account.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.darren.ms.vb.account.model.entity.AccountBalanceEntity;

public interface AccountBalanceRepository extends JpaRepository<AccountBalanceEntity, Long> {
}
