public class Account {




    private String accountType;
    private double currentBalance;
    private Customer customer;




    public Account(String type, Customer customer) {
        accountType = type;
        currentBalance = 0;
        this.customer = customer;
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


