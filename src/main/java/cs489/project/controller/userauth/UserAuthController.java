package cs489.project.controller.userauth;

import cs489.project.dto.userauth.UserAuthRequest;
import cs489.project.utils.JWTManagementUtilityService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class UserAuthController {
    private JWTManagementUtilityService jwtManagementUtilityService;
    private AuthenticationManager authenticationManager;

    public UserAuthController(JWTManagementUtilityService jwtManagementUtilityService, AuthenticationManager authManager) {
        this.jwtManagementUtilityService = jwtManagementUtilityService;
        this.authenticationManager = authManager;
    }

    @PostMapping("/public/authenticate")
    public String authenticateUser(@RequestBody UserAuthRequest userauthRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userauthRequest.getUsername(),
                            userauthRequest.getPassword())
            );
        } catch (Exception e) {
            System.out.println("UserAuthException is: " + e);
            System.out.println("Invalid Username and/or Password!");
            throw e;
        }
        return jwtManagementUtilityService.generateToken(userauthRequest.getUsername());
    }
}
