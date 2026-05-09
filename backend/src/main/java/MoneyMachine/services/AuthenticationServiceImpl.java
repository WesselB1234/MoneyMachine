package MoneyMachine.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

import MoneyMachine.models.User;
import MoneyMachine.models.exceptions.ExpiredException;
import MoneyMachine.models.exceptions.NotAuthorizedException;
import MoneyMachine.repositories.UserRepository;
import MoneyMachine.services.Interfaces.AuthenticationService;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UserRepository userRepository;
    private String tokenSecretKey;
    private double tokenExpirationInHours;
    private final String[] requiredClaims = {"role", "email", "firstName", "lastName"};

    public AuthenticationServiceImpl(UserRepository userRepository, @Value("${TOKEN_SECRET_KEY}") String tokenSecretKey, @Value("${TOKEN_EXPIRATION_IN_HOURS}") double tokenExpirationInHours) {
        this.userRepository = userRepository;
        this.tokenSecretKey = tokenSecretKey;
        this.tokenExpirationInHours = tokenExpirationInHours;
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        
        User user = userRepository.findByEmail(email);

        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }

        return null;
    }

    @Override
    public String generateTokenFromUser(User user) {

        Algorithm algorithm = Algorithm.HMAC256(this.tokenSecretKey);
        
        return JWT.create()
            .withSubject(user.getId().toString())
            .withClaim("role", user.getRole().toString())
            .withClaim("email", user.getEmail())
            .withClaim("firstName", user.getFirstName())
            .withClaim("lastName", user.getLastName())
            .withExpiresAt(new Date(System.currentTimeMillis() + (long)(3600000 * tokenExpirationInHours)))
            .sign(algorithm);
    }

    @Override
    public String getHashedPassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(10));
    }

    @Override
    public DecodedJWT getDecodedToken(String token) {
        return JWT.decode(token);
    }

    @Override
    public void validateDecodedToken(DecodedJWT decoded) {

        if (decoded.getSubject() == null) {
            throw new NotAuthorizedException("Token is missing subject.");
        }

        if (decoded.getExpiresAt() == null) {
            throw new NotAuthorizedException("Token is missing expiration.");
        }

        if (decoded.getExpiresAt().before(new Date())) {
            throw new ExpiredException("Token has expired.");
        }

        for (String requiredClaim : requiredClaims) {
             if (isClaimNullOrEmpty(decoded.getClaim(requiredClaim))) {
                throw new NotAuthorizedException(String.format("Token is missing %s claim.", requiredClaim));
            }
        }
    }

    private boolean isClaimNullOrEmpty(Claim claim) {
        return claim == null || claim.isNull() || claim.isMissing() || claim.asString() == null || claim.asString().isBlank();
    }
}
