package MoneyMachine.repositories;

import java.math.BigDecimal;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import MoneyMachine.models.BankAccount;

@Repository
public interface BankAccountRepository extends ListCrudRepository<BankAccount, String> {
    @Query("UPDATE BankAccount b SET b.balance = b.balance - ?2 WHERE b.accountId = ?1")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void pay(String accountId, BigDecimal amount);
    @Query("UPDATE BankAccount b SET b.balance = b.balance + ?2 WHERE b.accountId = ?1")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public void receive(String accountId, BigDecimal amount);
}
