package in.sp.main.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Usercontroller {
	@GetMapping("/profile")
    public String userProfile() {
        return "Welcome to USER Profile";
    }
}
