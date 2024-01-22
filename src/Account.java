public class Account {




    private String accountType;
    private double currentBalance;
    private Customer customer;




    public Account(String type, Customer customer) {
        this.accountType = type;
        this.customer = customer;
        currentBalance = 0;
    }


    public String getAccountType() {
        return accountType;
    }


    public double getCurrentBalance() {
        return currentBalance;

    }


    public void setCurrentBalance(double amount) {
        currentBalance += amount;
    }


    public Customer getCustomer() {
        return customer;
    }
}


