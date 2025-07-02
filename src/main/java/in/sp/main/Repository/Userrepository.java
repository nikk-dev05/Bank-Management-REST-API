package in.sp.main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.sp.main.entity.User;

@Repository
public interface Userrepository extends JpaRepository<User, Integer> {
    
    
	Optional<User> findByName(String name);
	boolean existsByName(String name);

}

