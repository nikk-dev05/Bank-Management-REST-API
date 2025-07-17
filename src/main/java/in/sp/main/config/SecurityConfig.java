package in.sp.main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.sp.main.Filter.JwtFilter;
import in.sp.main.Repository.User_Inforepo;
import in.sp.main.entity.User1;
import in.sp.main.services.User1service;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
   @Autowired
	private  JwtFilter jwtFilter;
   @Autowired
   private User1service user1service;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return  http
            .csrf(csrf->csrf.disable())
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers("/auth/**").permitAll()       
            		.requestMatchers(HttpMethod.DELETE, "/bank/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/bank/all").hasRole("ADMIN")
            	    .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();

    }
    @Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    @Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(user1service);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
    @Bean
	public AuthenticationManager  authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		 return authenticationConfiguration.getAuthenticationManager();
	}
    @Bean
    CommandLineRunner createDefaultUser(User_Inforepo repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsername("nikhil") == null) {
                User1 user = new User1();
                user.setUsername("nikhil");
                user.setPassword(encoder.encode("1234")); 
                user.setRoles("ROLE_USER");
                repo.save(user);
                System.out.println("Default user created.");
            }
            if (repo.findByUsername("admin").isEmpty()) {
                User1 admin = new User1();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123")); 
                admin.setRoles("ROLE_ADMIN");
                repo.save(admin);
                System.out.println("ROLE_ADMIN: admin created.");
            }
        };
    }

}


