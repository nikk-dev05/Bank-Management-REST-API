package in.sp.main.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import in.sp.main.entity.Bank;

public interface Bankrepository extends JpaRepository<Bank, Integer> {

   
    Page<Bank> findAll(Pageable pageable);
}

