import java.io.Serializable;
import java.util.ArrayList;


public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    private double balance;
    private int customerId;
    private String nationalid;
    private String name;
    private String telephone;
    private String email;

    public Customer(double balance, String nationalid, String name,String telephone,String email, int customerId) {
        this.nationalid = nationalid;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.balance=balance;
        this.customerId=customerId;
    }
    public double getBalance() {
        return balance;
    }

    public int getId() {
        return customerId;
    }

    public int getCustomerId(){ return customerId; }

    public String getNationalid() {
        return nationalid;
    }

    public void setNationalid(String nationalid) {
        this.nationalid = nationalid;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}

    public String getTelephone() {return telephone;}

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}
