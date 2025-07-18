package in.sp.main.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import in.sp.main.entity.Bank;
import in.sp.main.entity.User1;

public interface Bankrepository extends JpaRepository<Bank, Integer> {

   
    Page<Bank> findAll(Pageable pageable);
    Optional<Bank> findByUser(User1 user);
  //  Optional<User1> findByUsername(String username);


   

}

