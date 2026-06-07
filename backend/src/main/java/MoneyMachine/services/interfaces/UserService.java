package MoneyMachine.services.interfaces;

import MoneyMachine.models.dtos.responses.UserOverviewResponse;
import org.springframework.data.domain.Pageable;
import MoneyMachine.models.User;

public interface UserService {
    UserOverviewResponse getAllUsersWithoutBankAccounts(Pageable pageable);
    User getUserById(Long id); 
    void approveUser(User user);
}
