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
        System.out.println(currentBalance + "get bal");
        return this.currentBalance;

    }


    public void setCurrentBalance(double amount) {
        this.currentBalance += amount;
        System.out.println(currentBalance);
    }


    public Customer getCustomer() {
        return customer;
    }
}


