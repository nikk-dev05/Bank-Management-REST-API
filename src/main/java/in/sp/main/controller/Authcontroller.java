package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import in.sp.main.entity.User;
import in.sp.main.services.Userservice;

@RestController
public class Authcontroller {
     @Autowired
	private Userservice userservice;
    
	@PostMapping("/user/signup")
	public ResponseEntity<String> create(@RequestBody User user){
		if (user.getName() == null || user.getName().trim().isEmpty() ||
			    user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			    throw new RuntimeException("Fill all the fields properly");
			}
		

		             userservice.create_user(user);
		    return ResponseEntity.ok().body("User registered succeessfully ");
		
	}
	@PostMapping("/user/login")
	public ResponseEntity<String> login(@RequestBody User user){
		  Boolean login = userservice.login_user(user.getName(), user.getPassword());
		  if(login) {
			  return ResponseEntity.ok("User login succeessfullly.....");
		  }
		  else {
			  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invaild password or name");
		  }
	}
	 @ExceptionHandler(RuntimeException.class)
	 @ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
	 public String  handler(Exception e) {
		  return  e.getMessage();
	 }
	
	
	
	
	
	
	
	
	
}


