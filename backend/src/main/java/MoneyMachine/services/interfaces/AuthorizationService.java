package MoneyMachine.services.interfaces;

import org.springframework.security.core.Authentication;

public interface AuthorizationService {
    public Boolean isAllowedToGetUserById(Authentication authentication, Integer id);
}
