package MoneyMachine.services.interfaces;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import MoneyMachine.models.dtos.responses.DepositTransactionResponse;
import MoneyMachine.models.dtos.responses.TransactionoverviewResponse;
import MoneyMachine.models.dtos.responses.ITransactionResponse;

import MoneyMachine.models.dtos.responses.TransferTransactionResponse;
import MoneyMachine.models.dtos.responses.WithdrawTransactionResponse;

@Service
public interface TransactionService {
    TransferTransactionResponse transferAmountBetweenBankAccounts(String fromIban, String toIban, BigDecimal amount, String message);
    DepositTransactionResponse depositAmountIntoBankAccount(String toIban, BigDecimal amount);
    WithdrawTransactionResponse withdrawAmountIntoBankAccount(String fromIban, BigDecimal amount);
    public TransactionoverviewResponse getAllTransactions(Pageable pageable);
    public TransactionoverviewResponse getTransactionsByIban(String iban,Pageable pageable);
    public ITransactionResponse getTransactionByid(long id);
    
}
