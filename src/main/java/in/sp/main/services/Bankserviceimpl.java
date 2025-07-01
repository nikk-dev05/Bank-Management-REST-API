package in.sp.main.services;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import in.sp.main.Repository.Bankrepository;
import in.sp.main.entity.Bank;

@Service
public class Bankserviceimpl implements Bankservice {
     @Autowired
	private Bankrepository bankrepository;
	@Override
	public Bank create_account(Bank bank) {
	
		return  bankrepository.save(bank);
	}
	@Override
	public Bank getAccountdetailsByNmuber(int id) {
	   Bank get_Details= bankrepository.findById(id).orElseThrow(()-> new RuntimeException("Account not present with this id :"+id) );
	 
	  
		  return get_Details;
	  
	   
			}
	@Override
	public List<Bank> getAccountDetailsAll() {
	
		
		List<Bank> user = bankrepository.findAll();
		return user;
	}
	@Override
	public Bank depositMoney(int id, double amount) {
	  Bank money_added= bankrepository.findById(id).orElseThrow(()-> new RuntimeException("Account not found with this id"+id));
				             Double money = money_added.getAccount_balance();
		               money_added.setAccount_balance(money + amount );
		             return bankrepository.save(money_added);
	
	}
	@Override
			    
	public Bank withdrawMoney(int id, double amount) {
	    Bank account = bankrepository.findById(id)
	        .orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
	    
	    double currentBalance = account.getAccount_balance();
	    
	    if (amount > currentBalance) {
	        throw new RuntimeException("Insufficient Balance");
	    }
	    
	    account.setAccount_balance(currentBalance - amount);
	    return bankrepository.save(account);
	}
    
		   	 
			
	@Override
	public void deleteAccount(int id) {
	     
		Bank account = bankrepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found with ID: " + id));
			bankrepository.delete(account);

	}

	@Override
	public Page<Bank> getPaginatedAccounts(Pageable pageable) {
	
		return bankrepository.findAll(pageable);
	}
	
	

}
