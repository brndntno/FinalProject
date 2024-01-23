public class Account {
    private String accountType;
    private double currentBalance;
    private Customer customer;

    // initializes account type, customer, and account balance
    public Account(String type, Customer customer) {
        this.accountType = type;
        this.customer = customer;
        currentBalance = 0;
    }

    // gets the current account balance of an account
    public double getCurrentBalance() {
        return currentBalance;

    }

    // sets the current account balance of an account to the parameter plus whatever the current amount is
    public void setCurrentBalance(double amount) {
        currentBalance += amount;
    }
}
