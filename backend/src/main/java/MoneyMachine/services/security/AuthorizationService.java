package MoneyMachine.services.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    public Boolean isAllowedToGetUserById(Authentication authentication, Long id) {
        return false;
    }
}
