package in.sp.main.entity;

import jakarta.persistence.*;

@Entity
@Table
public class Bank {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number") // DB column name
    private int accountNumber;        // ✅ Java field name

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "account_balance")
    private double accountBalance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User1 user;


    // Getters and Setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    public User1 getUser() {
        return user;
    }

    public void setUser(User1 user) {
        this.user = user;
    }
}

