package sg.darren.ms.vb.account.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.darren.ms.vb.account.model.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
