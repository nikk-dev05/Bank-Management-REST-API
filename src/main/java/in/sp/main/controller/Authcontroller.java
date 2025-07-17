package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import in.sp.main.Repository.User_Inforepo;
import in.sp.main.entity.User1;

import in.sp.main.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
   private User_Inforepo user_Inforepo;  

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User1 user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // ✅ Get userId from DB
        User1 loggedInUser = user_Inforepo.findByUsername(user.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Generate token with userId included
        String token = jwtService.generateToken(userDetails, loggedInUser.getId());

        return ResponseEntity.ok(token);
    }
}
