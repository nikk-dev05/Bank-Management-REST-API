package in.sp.main.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.Repository.Userrepository;
import in.sp.main.entity.User;

@Service
public  class Userserviceimpl implements Userservice{
     @Autowired
	private Userrepository userrepository;
	@Override
	public User create_user(User user) {
		if(userrepository.existsByName(user.getName())) {
			throw new RuntimeException("provide  another username ");
		}
		
		return userrepository.save(user);
	}
	@Override
	public Boolean login_user(String name, String password) {
		   User valid_User =  userrepository.findByName(name);
		   if(valid_User==null) {
			    return false;
		   }
		   
			   return valid_User.getPassword().equals(password);
			  
		   
		   
	
	}
	
	
	
}