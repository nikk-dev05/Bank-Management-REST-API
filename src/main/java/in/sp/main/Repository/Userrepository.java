package in.sp.main.Repository;



import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.User;

public interface Userrepository extends JpaRepository<User, Integer> {
      boolean  existsByName(String name);
      User  findByName(String name);
     
}
