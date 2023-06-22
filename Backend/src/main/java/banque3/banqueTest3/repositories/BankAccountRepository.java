package banque3.banqueTest3.repositories;

import banque3.banqueTest3.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount,String > {
}
