package MoneyMachine.factories;

import MoneyMachine.models.enums.BankAccountType;
import MoneyMachine.strategies.interfaces.BankAccountTypeStrategy;
import MoneyMachine.strategies.CheckingStrategy;
import MoneyMachine.strategies.SavingsStrategy;

public class BankAccountTypeFactory {
    public static BankAccountTypeStrategy bankAccountRules(BankAccountType bankAccountType)
    {
        switch(bankAccountType)
        {
            case SAVINGS:
                return new SavingsStrategy();
            case CHECKING:
                return new CheckingStrategy();
            default:
                throw new IllegalArgumentException("Unknown bankaccount type:" + bankAccountType);
        }
    } 
}