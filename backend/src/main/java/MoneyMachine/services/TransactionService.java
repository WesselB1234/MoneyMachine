package main.java.MoneyMachine.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import MoneyMachine.models.Transaction;
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
       return transactionRepository.findAll().orElseThrow();
    }
    public List<Transaction> getAllTransactionsByAccountId(int accountId)
    {
        List<Transaction> transactions = new  ArrayList<Transaction>();
        List<Transaction> fromTransactions = new  ArrayList<Transaction>();
        List<Transaction> toTransactions = new  ArrayList<Transaction>();

        fromTransactions.addAll(transactionRepository.getTransactionByFromAccountId(accountId).orElseThrow());
        toTransactions.addAll(transactionRepository.getTransactionByToAccountId(accountId).orElseThrow());

        transactions.addAll(fromTransactions);
        transactions.addAll(toTransactions);
        return transactions;
    }
    public Transaction getTransactionById(int transactionId)
    {
       return transactionRepository.findById(transactionId).orElseThrow();
    }
    @Transactional(rollbackFor = Exception.class)
    public Transaction createTransaction(Transaction transaction)
    {
        bankAccountRepository.pay(transaction.getFromBankAccount().getAccountId(), transaction.getAmount());
        bankAccountRepository.receive(transaction.getToBankAccount().getAccountId(), transaction.getAmount());
        return transactionRepository.save(transaction).orElseThrow();
    }  
}
