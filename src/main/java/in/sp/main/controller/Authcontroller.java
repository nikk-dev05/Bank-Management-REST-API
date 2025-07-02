package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import in.sp.main.entity.User;
import in.sp.main.services.Userservice;
import jakarta.validation.Valid;



@RestController
@Validated
public class Authcontroller {

    @Autowired
    private Userservice userservice;

    @PostMapping("/user/signup")
    public ResponseEntity<String> create(@Valid @RequestBody User user) {
        String message =  userservice.create_user(user);
        return ResponseEntity.ok().body(message);
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean login = userservice.login_user(user.getName(), user.getPassword());
        if (login) {
            return ResponseEntity.ok("User logged in successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
        }
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
    public String handler(Exception e) {
        return e.getMessage();
    }
}

