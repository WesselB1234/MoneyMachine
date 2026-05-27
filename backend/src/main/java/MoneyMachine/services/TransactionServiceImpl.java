package MoneyMachine.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import MoneyMachine.models.DepositTransaction;
import MoneyMachine.models.WithdrawTransaction;
import MoneyMachine.services.interfaces.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Override
    public DepositTransaction depositAmountIntoBankAccount(String iban, BigDecimal amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'depositAmountIntoBankAccount'");
    }

    @Override
    public WithdrawTransaction withdrawAmountIntoBankAccount(String iban, BigDecimal amount) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdrawAmountIntoBankAccount'");
    }
}
