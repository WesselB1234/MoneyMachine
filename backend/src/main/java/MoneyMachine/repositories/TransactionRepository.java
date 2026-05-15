package MoneyMachine.repositories;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import MoneyMachine.models.Transaction;

@Repository
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {
    List<Transaction> getTransactionByFromIban(String iban);
    List<Transaction> getTransactionByToIban(String iban);
    

    
}
