package MoneyMachine.services.interfaces;

import MoneyMachine.models.dtos.requests.BankAccountCreationRequest;
import MoneyMachine.models.dtos.requests.PatchRequest;
import MoneyMachine.models.dtos.responses.BankAccountOverviewResponse;
import MoneyMachine.models.dtos.responses.BankAccountResponse;
import MoneyMachine.models.enums.BankAccountType;

import org.springframework.data.domain.Pageable;

import MoneyMachine.models.User;
public interface BankAccountService {
    BankAccountResponse createBankAccountFromRequest(BankAccountCreationRequest bankAccountCreationRequest);
    BankAccountResponse createBankAccountForUser(BankAccountType bankAccountType, User user);
    BankAccountOverviewResponse getAllBankAccounts(Pageable pageable);
    BankAccountResponse getBankAccountByIban(String iban);
    BankAccountResponse closeBankAccount(PatchRequest patchRequest, String iban);
}
