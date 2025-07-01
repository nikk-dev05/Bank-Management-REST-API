package in.sp.main.services;

import java.util.List;

import org.springframework.data.domain.Page;

import in.sp.main.entity.Bank;

public interface Bankservice {

	public Bank create_account(Bank bank);
	public Bank getAccountdetailsByNmuber(int id);
	public List<Bank> getAccountDetailsAll();
	public Bank depositMoney(int id, double amount);
	public Bank withdrawMoney(int id,double amount);
	public void deleteAccount(int id);
	public  Page<Bank> get_All(int pageno,int pagesize);

}
