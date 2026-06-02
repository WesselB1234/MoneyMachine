package MoneyMachine.controllers;

import java.util.HashMap;
import java.util.Map;

import MoneyMachine.models.enums.LoginType;
import MoneyMachine.models.enums.Role;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import MoneyMachine.models.User;
import MoneyMachine.util.JwtUtil;

import org.springframework.http.MediaType;

import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.greaterThan;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private ObjectMapper objectMapper;

    private String validAtmToken;
    private User loggedInAtmUser;

    @BeforeEach
    void setUp() {
        loggedInAtmUser = new User();
        loggedInAtmUser.setId(1L);
        loggedInAtmUser.setFirstName("userFirstName");
        loggedInAtmUser.setLastName("userLastName");
        loggedInAtmUser.setEmail("user@user.user");
        loggedInAtmUser.setRole(Role.USER);
    
        validAtmToken = jwtUtil.generateAuthTokenFromUser(loggedInAtmUser, LoginType.ATM);
    }

    @Test
    void login_whenLoginAsUser_getAuthenticationResponse() throws Exception {

        Map<String, Object> request = new HashMap<>();
        request.put("email", "user@user.user");
        request.put("password", "password");
        request.put("loginType", "ATM");

        mockMvc.perform(post("/users/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is(201))
            .andExpect(jsonPath("$.accessToken").exists())
            .andExpect(jsonPath("$.expiresIn").exists())
            .andExpect(jsonPath("$.userSummaryResponse").exists())
            .andExpect(jsonPath("$.userSummaryResponse.id").value(1));
    }

    @Test
    void invalidLogin_whenInvalidPassword_returnInvalidCredentials() throws Exception {

        Map<String, Object> request = new HashMap<>();
        request.put("email", "user@user.user");
        request.put("password", "invalidPassword");
        request.put("loginType", "ATM");

        mockMvc.perform(post("/users/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().is(401));
    }

    @Test
    void getBankAccountsByUserId_whenGetCall_getBankAccounts() throws Exception {
        mockMvc.perform(get(String.format("/users/%s/bank-accounts", loggedInAtmUser.getId()))
                .header("Authorization", "Bearer " + validAtmToken))
            .andExpect(status().is(200))
            .andExpect(jsonPath("$.items").exists())
            .andExpect(jsonPath("$.items.length()").value(Matchers.greaterThan(0)));
    }
}
