package in.sp.main.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.entity.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	List<Transaction> findByBankAccountNumber(int accountNumber);

}
