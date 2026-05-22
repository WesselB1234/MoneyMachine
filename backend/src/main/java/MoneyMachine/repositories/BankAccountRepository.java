package MoneyMachine.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import MoneyMachine.models.BankAccount;
import jakarta.persistence.LockModeType;

@Repository
public interface BankAccountRepository extends ListCrudRepository<BankAccount, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT b FROM BankAccount b WHERE b.iban = :iban")
    Optional<BankAccount> findByIdForUpdate(String iban);
    
}
