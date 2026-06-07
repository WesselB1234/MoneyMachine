package MoneyMachine.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import MoneyMachine.mappers.BankAccountMapper;
import MoneyMachine.models.BankAccount;
import MoneyMachine.repositories.BankAccountRepository;
import MoneyMachine.models.User;
import MoneyMachine.models.dtos.requests.PatchRequest;
import MoneyMachine.models.dtos.responses.BankAccountOverviewResponse;
import MoneyMachine.models.dtos.responses.BankAccountResponse;
import MoneyMachine.models.enums.BankAccountType;
import MoneyMachine.models.enums.Role;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class BankAccountServiceImplTest {

    @Mock
    private BankAccountMapper bankAccountMapper;
    @Mock
    private BankAccountRepository bankAccountRepository;
    @InjectMocks
    private BankAccountServiceImpl bankAccountServiceImpl;

    @InjectMocks
    private BankAccount bankAccount;

    private BankAccount savingsBankAccount;

    private User user;

    @Mock
    private Page<BankAccount> page;

    @Mock
    private List<BankAccount> bankAccounts;

    private PatchRequest patchRequest;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstName("employeeFirstName");
        user.setLastName("employeeLastName");
        user.setEmail("employee@employee.employee");
        user.setBsn("123456749");
        user.setPhoneNumber("+31 6 12 34 54 78");
        user.setRole(Role.EMPLOYEE);
        user.setPassword("MockedPassword");
        user.setIsActive(false);
        user.setIsApproved(false);

        bankAccount = new BankAccount();
        bankAccount.setIban("NL91ABNA0417164300");
        bankAccount.setUser(user);
        bankAccount.setBalance(new BigDecimal("100"));
        bankAccount.setAbsoluteLimit(new BigDecimal("-100"));
        bankAccount.setSingleTransferLimit(new BigDecimal("100"));
        bankAccount.setDailyTransferLimit(new BigDecimal("100"));
        bankAccount.setBankAccountType(BankAccountType.CHECKING);
        bankAccount.setIsActive(true);

        savingsBankAccount = new BankAccount();
        savingsBankAccount.setIban("NL91ABN2417164300");
        savingsBankAccount.setUser(user);
        savingsBankAccount.setBalance(new BigDecimal("300"));
        savingsBankAccount.setAbsoluteLimit(new BigDecimal("0"));
        savingsBankAccount.setSingleTransferLimit(new BigDecimal("1000"));
        savingsBankAccount.setDailyTransferLimit(new BigDecimal("2000"));
        savingsBankAccount.setBankAccountType(BankAccountType.SAVINGS);
        savingsBankAccount.setIsActive(true);
        patchRequest = new PatchRequest();
        patchRequest.setActive(false);
    }

    @Test
    public void closeBankAccount_whenBankAccountIsClosed_setIsActiveFalseAndReturnUpdatedBankAccount() {
        String iban = bankAccount.getIban();
        when(bankAccountRepository.findById(iban)).thenReturn(Optional.of(bankAccount));

        BankAccountResponse expectedResponse = new BankAccountResponse();
        expectedResponse.setActive(false);

        when(bankAccountMapper.toResponse(bankAccount)).thenReturn(expectedResponse);
        expectedResponse = bankAccountServiceImpl.closeBankAccount(patchRequest,
                iban);

        assertNotNull(expectedResponse);
        
        verify(bankAccountRepository).findById(iban);
        verify(bankAccountRepository).save(bankAccount);
        verify(bankAccountMapper).toResponse(bankAccount);
    }

    @Test
    public void getAllBankAccounts_whenBankAccountsFound_returnsAllBankAccounts() {
        bankAccounts = List.of(bankAccount, savingsBankAccount);
        when(bankAccountRepository.findAll(pageable)).thenReturn(page);
        when(page.getContent()).thenReturn(bankAccounts);
        when(page.getNumber()).thenReturn(0);
        when(page.getSize()).thenReturn(2);

        List<BankAccountResponse> expectedBankAccounts = List.of(
            new BankAccountResponse(),
            new BankAccountResponse()
        );

        when(bankAccountMapper.toResponseList(bankAccounts)).thenReturn(expectedBankAccounts);

        BankAccountOverviewResponse bankAccountOverviewResponse = bankAccountServiceImpl.getAllBankAccounts(pageable);
        assertEquals(2, bankAccountOverviewResponse.getItems().size());
        assertEquals(0, bankAccountOverviewResponse.getPage());
        assertEquals(2, bankAccountOverviewResponse.getPageSize());
        assertNotNull(bankAccountOverviewResponse);
        verify(bankAccountRepository).findAll(pageable);
        verify(bankAccountMapper).toResponseList(bankAccounts);
    }

    public void testGetAllBankAccountsByUserId() {

    }

    @Test
    void testGetBankAccountByIban() {

    }

    @Test
    void testGetBankAccountByIbanAndUserId() {

    }

    @Test
    void testSetBankAccountBalance() {

    }
}
