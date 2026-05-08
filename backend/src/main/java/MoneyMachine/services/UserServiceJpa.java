package MoneyMachine.services;

import java.util.List;

import org.springframework.stereotype.Service;
import MoneyMachine.models.User;
import MoneyMachine.repositories.UserRepository;
import MoneyMachine.services.interfaces.UserService;
import java.util.ArrayList;

@Service
public class UserServiceJpa implements UserService {
    private UserRepository userRepository;

    public UserServiceJpa(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsersWithoutBankAccounts() {
        Iterable<User> users = userRepository.findByBankAccountsIsEmpty();
        List<User> convertedUsers = new ArrayList<User>();

        for(User user : users)
        {
            convertedUsers.add(user);
        }
        return convertedUsers;
    }
}
