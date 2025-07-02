package in.sp.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.sp.main.Repository.Userrepository;
import in.sp.main.entity.User;

@Service
public class Userserviceimpl implements Userservice {

    @Autowired
    private Userrepository userrepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String create_user(User user) {
        if (userrepository.existsByName(user.getName())) {
            throw new RuntimeException("Username already exists. Please choose another.");
        }

        // Encode the password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userrepository.save(user);
        return "User registered successfully.";
    }

    @Override
    public Boolean login_user(String name, String password) {
        Optional<User> userOpt = userrepository.findByName(name);
        if (userOpt.isEmpty()) {
            return false;
        }

        User user = userOpt.get();
        return passwordEncoder.matches(password, user.getPassword());
    }
}
