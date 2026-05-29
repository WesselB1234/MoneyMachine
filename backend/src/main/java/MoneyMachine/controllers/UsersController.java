package MoneyMachine.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import MoneyMachine.exception.InvalidCredentialsException;
import MoneyMachine.exception.NotFoundException;
import MoneyMachine.mappers.UserMapper;
import MoneyMachine.models.User;
import MoneyMachine.models.dtos.requests.LoginRequest;
import MoneyMachine.models.dtos.responses.LoginResponse;
import MoneyMachine.models.dtos.responses.UserOverviewResponse;
import MoneyMachine.models.dtos.responses.UserResponse;
import MoneyMachine.services.interfaces.*;
import MoneyMachine.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UsersController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    public UsersController(UserService userService, AuthenticationService authenticationService, UserMapper userMapper,
            JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.userMapper = userMapper;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {

        User user = authenticationService.getUserByEmailAndPassword(loginRequest.getEmail(),
                loginRequest.getPassword());

        if (user == null) {
            throw new InvalidCredentialsException("Password or username is not correct.");
        }

        String authToken = jwtUtil.generateAuthTokenFromUser(user, loginRequest.getLoginType());

        LoginResponse loginResponse = new LoginResponse(authToken, "Bearer", jwtUtil.getAuthTokenExpirationTime(),
                userMapper.toSummaryResponse(user));

        return ResponseEntity.status(201).body(loginResponse);
    }

    @GetMapping("me")
    public ResponseEntity<?> getLoggedInUser(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        User user = authenticationService.getLoggedInUser();
        UserResponse userResponse = userMapper.toResponse(user);

        return ResponseEntity.status(200).body(userResponse);
    }

    @GetMapping("employee-test")
    @PreAuthorize("hasRole('EMPLOYEE') && @authorizationService.isLoggedIntoLoginType('WEBSITE')")
    public ResponseEntity<String> employeeTest() {
        return ResponseEntity.status(200).body("You have the employee super powers that can power every power in the power universe and are website logged in.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("@authorizationService.isAllowedToGetUserById(#id)")
    public ResponseEntity<String> getUserByIdTest(@PathVariable Long id) {
        return ResponseEntity.status(200).body("This is a test involving authorization based on conditionals put in @PreAuthorize and AuthorizationService.java.");
    }

    @GetMapping()
    @PreAuthorize("hasRole('EMPLOYEE') && @authorizationService.isLoggedIntoLoginType('WEBSITE')")
    public ResponseEntity<?> getAllUsersWithoutAnAccount() {
            List<UserResponse> users = userService.getAllUsersWithoutBankAccounts();
            if(users == null)
            {
                throw new NotFoundException("There are no users found in the database");
            }
            UserOverviewResponse userOverviewResponse = new UserOverviewResponse();
            userOverviewResponse.setUsers(users);
            return ResponseEntity.ok(userOverviewResponse);
    }
}
