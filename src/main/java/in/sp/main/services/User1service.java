package in.sp.main.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.sp.main.Repository.User_Inforepo;
import in.sp.main.entity.User1;
import in.sp.main.entity.User_Principle;


@Service
public class User1service  implements UserDetailsService{

	@Autowired
	private User_Inforepo user_Inforepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<User1> user=	user_Inforepo.findByUsername(username);
		  if(user==null) {
			  throw new  UsernameNotFoundException("user not found ");
		  }
		     
		  return new User_Principle(user.get());
	}

}
