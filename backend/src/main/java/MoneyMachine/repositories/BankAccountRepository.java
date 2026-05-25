package MoneyMachine.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import MoneyMachine.models.BankAccount;

@Repository
public interface BankAccountRepository extends ListCrudRepository<BankAccount, String> {
    public List<BankAccount> findAllByUserId(Long id);
    public Optional<BankAccount> findByIbanAndUserId(String iban, Long id);
}
