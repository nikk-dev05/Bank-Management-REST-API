package in.sp.main.services;

import in.sp.main.entity.User;

public interface Userservice {
       public User create_user(User user);
      public Boolean  login_user(String name,String password);
}
