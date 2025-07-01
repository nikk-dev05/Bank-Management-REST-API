package in.sp.main.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import in.sp.main.entity.Bank;
import in.sp.main.exception.Myexception;
import in.sp.main.services.Bankservice;

@RestController
@RequestMapping("/bank")
public class Mycontroller {

    @Autowired
    private Bankservice bankservice;

    // Create bank account
    @PostMapping
    public ResponseEntity<Bank> createAccount( @RequestBody Bank bank) {
        if (bank.getAccount_holder_name() == null || bank.getAccount_holder_name().trim().isEmpty()
                || bank.getAccount_balance() <= 0) {
            throw new Myexception("All fields are mandatory and balance must be greater than 0");
        }
        Bank createdAccount = bankservice.create_account(bank);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    // Get account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Bank> getDetails(@PathVariable int id) {
        Bank details = bankservice.getAccountdetailsByNmuber(id);
        return ResponseEntity.ok(details);
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<Bank>> getAllDetails() {
        List<Bank> userDetails = bankservice.getAccountDetailsAll();
        return ResponseEntity.ok(userDetails);
    }

    // Deposit money
    @PutMapping("/{id}/deposit")
    public ResponseEntity<Bank> depositMoney(@PathVariable int id, @RequestParam double amount) {
        Bank updated = bankservice.depositMoney(id, amount);
        return ResponseEntity.ok(updated);
    }

    // Withdraw money
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<Bank> withdrawMoney(@PathVariable int id, @RequestParam double amount) {
        Bank updated = bankservice.withdrawMoney(id, amount);
        return ResponseEntity.ok(updated);
    }

    // Get paginated results
    @GetMapping("/page/{pageNo}/size/{pageSize}")
    public ResponseEntity<Page<Bank>> getPaginatedAccounts(@PathVariable int pageNo, @PathVariable int pageSize) {
        Page<Bank> result = bankservice.get_All(pageNo, pageSize);
        return ResponseEntity.ok(result);
    }

    // Delete account
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable int id) {
        bankservice.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully.");
    }

    // Handle custom exceptions
    @ExceptionHandler(Myexception.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String handleCustomException(Myexception e) {
        return e.getMsg();
    }

    // Handle other runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRuntimeException(Exception e) {
        return e.getMessage();
    }
}
