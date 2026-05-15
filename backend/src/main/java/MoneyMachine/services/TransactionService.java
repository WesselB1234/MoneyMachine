package MoneyMachine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import MoneyMachine.models.Transaction;
import MoneyMachine.models.TransferTransaction;
import MoneyMachine.repositories.BankAccountRepository;
import MoneyMachine.repositories.TransactionRepository;
@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private BankAccountRepository bankAccountRepository;

    public TransactionService(TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<Transaction> getAllTransactions()
    {
       return transactionRepository.findAll();
    }
    public List<Transaction> getAllTransactionsByAccountId(String iban)
    {
        List<Transaction> transactions = new  ArrayList<Transaction>();
        List<Transaction> fromTransactions = new  ArrayList<Transaction>();
        List<Transaction> toTransactions = new  ArrayList<Transaction>();

        fromTransactions.addAll(transactionRepository.getTransactionByFromIban(iban));
        toTransactions.addAll(transactionRepository.getTransactionByToIban(iban));

        transactions.addAll(fromTransactions);
        transactions.addAll(toTransactions);
        return transactions;
    }
    public Transaction getTransactionByid(long id)
    {
       return transactionRepository.findById(id).orElseThrow();
    }
    @Transactional(rollbackFor = Exception.class)
    public TransferTransaction createTransaction(TransferTransaction transaction)
    {
        bankAccountRepository.pay(transaction.getFromBankAccount().getIban(), transaction.getAmount());
        bankAccountRepository.receive(transaction.getToBankAccount().getIban(), transaction.getAmount());
        return transactionRepository.save(transaction);
    }  
}
