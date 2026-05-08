package MoneyMachine.services.interfaces;

import java.util.List;
import MoneyMachine.models.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    List<User> getAllUsersWithoutBankAccounts();
}
