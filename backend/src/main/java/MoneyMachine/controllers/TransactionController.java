package MoneyMachine.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MoneyMachine.models.DepositTransaction;
import MoneyMachine.models.WithdrawTransaction;
import MoneyMachine.models.dtos.requests.DepositRequest;
import MoneyMachine.models.dtos.requests.WithdrawRequest;
import MoneyMachine.services.interfaces.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("deposit")
    public ResponseEntity<?> deposit(@RequestBody DepositRequest depositRequest) {
        
        //DepositTransaction transaction = transactionService.depositAmountIntoBankAccount(null, null);

        return null;
    }

    @PostMapping("withdraw")
    public ResponseEntity<WithdrawTransaction> withdraw(@RequestBody WithdrawRequest withdrawRequest) {
        return null;
    }
}