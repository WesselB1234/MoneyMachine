package MoneyMachine.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.auth0.jwt.interfaces.DecodedJWT;

import MoneyMachine.models.User;
import MoneyMachine.models.dtos.ErrorDTO;
import MoneyMachine.models.enums.ErrorType;
import MoneyMachine.models.exceptions.ExpiredException;
import MoneyMachine.models.exceptions.NotAuthorizedException;
import MoneyMachine.models.exceptions.SignatureInvalidException;
import MoneyMachine.services.Interfaces.AuthenticationService;
import MoneyMachine.services.Interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

@Controller
@CrossOrigin(origins = "*", exposedHeaders = {"x-auth-error", "Authorization"})
@NoArgsConstructor
public class BaseController {
    
    User loggedInUser = null;
    UserService userService;
    AuthenticationService authenticationService;

    public BaseController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    private ErrorDTO setLoggedInUser(HttpServletRequest request, HttpServletResponse response) {

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null) {
                throw new NotAuthorizedException("Authorization header is required.");
            }

            String[] headerParts = authHeader.split(" ");

            if (headerParts.length != 2 || !headerParts[0].equalsIgnoreCase("bearer")) {
                throw new NotAuthorizedException("Invalid authorization header format.");
            }

            String token = headerParts[1];
            DecodedJWT decoded = authenticationService.getDecodedToken(token);
            this.authenticationService.validateDecodedToken(decoded);

            this.loggedInUser = this.userService.findUserById(Integer.parseInt(decoded.getSubject()));

            if (this.loggedInUser == null) {
                throw new NotAuthorizedException("User in your token does not exist.");
            }
        } 
        catch (ExpiredException ex) {
            return authError(response, "Token expired", ex.getMessage());
        } 
        catch (SignatureInvalidException ex) {
            return authError(response, "Invalid signature", ex.getMessage());
        } 
        catch (NotAuthorizedException ex) {
            return authError(response, "Not authorized", ex.getMessage());
        } 
        
        return null;
    }

    private ErrorDTO authError(HttpServletResponse response, String message, String detail) {
        response.setHeader("x-auth-error", "invalid_token");
        return new ErrorDTO(401, ErrorType.UNAUTHORIZED, message, detail);
    }

    public ErrorDTO atmLoggedInAuthorization(HttpServletRequest request, HttpServletResponse response){
        return this.setLoggedInUser(request, response);
    }
}
