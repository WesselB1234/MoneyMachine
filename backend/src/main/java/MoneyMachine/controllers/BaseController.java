package MoneyMachine.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.auth0.jwt.interfaces.DecodedJWT;

import MoneyMachine.models.User;
import MoneyMachine.models.dtos.ErrorDTO;
import MoneyMachine.models.enums.ErrorType;
import MoneyMachine.models.enums.LoginType;
import MoneyMachine.models.exceptions.ExpiredException;
import MoneyMachine.models.exceptions.NotAuthorizedException;
import MoneyMachine.models.exceptions.SignatureInvalidException;
import MoneyMachine.services.Interfaces.AuthenticationService;
import MoneyMachine.services.Interfaces.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

@Controller
@CrossOrigin(origins = "*", exposedHeaders = {"x-atm-auth-error"})
@NoArgsConstructor
public class BaseController {
    
    User atmLoggedInUser = null;
    UserService userService;
    AuthenticationService authenticationService;

    public BaseController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    private ErrorDTO setLoggedInUserByLoginType(HttpServletRequest request, HttpServletResponse response, LoginType loginType) {
        try {
            String headerName = "";

            if (loginType == LoginType.atm) {
                headerName = "Authorization";
            }

            String authHeader = request.getHeader(headerName);

            if (authHeader == null) {
                throw new NotAuthorizedException(headerName + " header is required.");
            }

            String[] headerParts = authHeader.split(" ");

            if (headerParts.length != 2 || !headerParts[0].equalsIgnoreCase("bearer")) {
                throw new NotAuthorizedException("Invalid authorization header format.");
            }

            String authToken = headerParts[1];
            DecodedJWT decodedAuthToken = authenticationService.getDecodedAuthToken(authToken);
            this.authenticationService.validateDecodedAuthToken(decodedAuthToken);
            
            User user = this.userService.findUserById(Integer.parseInt(decodedAuthToken.getSubject()));
            
            if (user == null) {
                throw new NotAuthorizedException("User in your token does not exist.");
            }

            if (loginType == LoginType.atm) {
                this.atmLoggedInUser = user;
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

    public ErrorDTO setAtmLoggedInUser(HttpServletRequest request, HttpServletResponse response) {
        return setLoggedInUserByLoginType(request, response, LoginType.atm);
    }

    private ErrorDTO authError(HttpServletResponse response, String message, String detail) {
        response.setHeader("x-atm-auth-error", "invalid_token");
        return new ErrorDTO(401, ErrorType.UNAUTHORIZED, message, detail);
    }
}
