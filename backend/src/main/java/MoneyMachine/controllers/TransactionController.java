package MoneyMachine.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import MoneyMachine.models.Transaction;
import MoneyMachine.models.TransferTransaction;
import MoneyMachine.services.TransactionService;

@RestController
public class TransactionController {
    private TransactionService transactionService;
    
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;

    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/transactions/account/{accountId}")
    public List<Transaction> getTransactionsByAccountiban(@PathVariable String Iban) {
        return transactionService.getAllTransactionsByAccountId(Iban);
    }

    @PostMapping("/transactions")
    public TransferTransaction createTransaction(@RequestBody TransferTransaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionByid(id);
    }
}
