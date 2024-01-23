import java.util.Scanner;
//private instance variables
public class ATM {
    private String userName;
    private int userPIN;
    private int transactionHistoryNum = 0;
    private int transactionHistoryNum2 = 0;
    private Account savings;
    private Account checking;
    private Scanner scan;
    private Customer customer;
    private TransactionHistory TH;


    public void start() {
        accountInfo();
    }

    //initializes the private instance variables
    public void accountInfo() {
        scan = new Scanner(System.in);
        customer = new Customer(userName, userPIN);
        savings = new Account("savings", customer);
        checking = new Account("checking", customer);
        TH = new TransactionHistory("");
        scan = new Scanner(System.in);

        // welcomes user
        System.out.println("Welcome banker to Aurum Fundamentals! We are delighted to have you as a new member!");
        System.out.print("What would you like the name for your account to be? ");
        userName = scan.nextLine();
        System.out.print("Welcome " + userName + ". Please enter a 4-digit PIN for your new account: ");
        int maybePIN = scan.nextInt();
        scan.nextLine();
        if (String.valueOf(maybePIN).length() == 4) {
            userPIN = maybePIN;
        } else {
            System.out.println("It seems that PIN does not have 4 digits. \nPlease try again. ");
            accountInfo();
        }

        // confirms PIN
        System.out.print("Please re-enter your PIN: ");
        int PIN = scan.nextInt();
        scan.nextLine();
        if (PIN == customer.getPIN()) {
            accountActions();
        } else {
            System.out.println("Hm, it seems that is incorrect. \nPlease try again. ");
            accountInfo();
        }
    }
    public void accountActions() {

        // main menu
        System.out.println("1. Withdraw money\n" +
                "2. Deposit money\n" +
                "3. Transfer money between accounts\n" +
                "4. Get account balances\n" +
                "5. Get transaction history\n" +
                "6. Change PIN\n" +
                "7. Exit\n");

        System.out.print("Enter a number: ");
        int choice = scan.nextInt();
        scan.nextLine();
        if (choice == 1) {
            // withdraws money from savings or checking account
            // tells user to choose between savings or checking
            System.out.print("Which account would you like to withdraw money from? (savings/checking)");
            String account = scan.nextLine();
            if (account.equals("savings")) {
                System.out.print("How much money would you like to withdraw? You can only withdraw $5s and $20s and do not include $ in your answer. ");
                int withdraw = scan.nextInt();
                scan.nextLine();
                // makes sure there are sufficient funds and withdraw amount is a multiple of 5
                if (withdraw <= savings.getCurrentBalance() && withdraw % 5 == 0) {
                    System.out.print("How many $5s would you like? ");
                    int numFives = scan.nextInt();
                    scan.nextLine();
                    System.out.print("How many $20s would you like? ");
                    int num20s = scan.nextInt();
                    scan.nextLine();
                    // checks if the sum of the $5 and $20 bills add up to the withdrawal amount
                    if (numFives * 5 + num20s * 20 == withdraw) {
                        savings.setCurrentBalance(-withdraw);
                        transactionHistoryNum++;
                        System.out.println("A000" + transactionHistoryNum + ". Withdrew $" + withdraw + " from savings account");
                        // edits transaction history
                        TH.setTransactionHistory("A000" + transactionHistoryNum + ". Withdrew $" + withdraw + " from savings account\n");
                    } else {
                        System.out.println("Unfortunately, your bills did not add up. \nPlease try again. ");
                        accountActions();
                    }
                } else {
                    System.out.println("Insufficient funds or invalid withdraw amount ");
                    accountActions();
                }
            }
            if (account.equals("checking")) {
                System.out.print("How much money would you like to withdraw? You can only withdraw $5s and $20s and do not include $ in your answer. ");
                int withdraw = scan.nextInt();
                scan.nextLine();
                // makes sure there are sufficient funds and withdraw amount is a multiple of 5
                if (withdraw <= checking.getCurrentBalance() && withdraw % 5 == 0) {
                    System.out.print("How many $5s would you like? ");
                    int numFives = scan.nextInt();
                    scan.nextLine();
                    System.out.print("How many $20s would you like? ");
                    int num20s = scan.nextInt();
                    scan.nextLine();
                    // checks if the sum of the $5 and $20 bills add up to the withdrawal amount
                    if (numFives * 5 + num20s * 20 == withdraw) {
                        checking.setCurrentBalance(-withdraw);
                        transactionHistoryNum++;
                        System.out.println("A000" + transactionHistoryNum + ". Withdrew $" + withdraw + " from checking account");
                        // edits transaction history
                        TH.setTransactionHistory("A000" + transactionHistoryNum + ". Withdrew $" + withdraw + " from checking account\n");
                    } else {
                        System.out.println("Unfortunately, your bills did not add up. \nPlease try again. ");
                        accountActions();
                    }
                } else {
                    System.out.println("Insufficient funds or invalid withdraw amount");
                    accountActions();
                }
            }
            accountActions();
        }
        if (choice == 2) {
            // deposits money into savings or checking account
            System.out.print("Which account would you like to deposit money into? (savings/checking)");
            String account = scan.nextLine();
            if (account.equals("savings")) {
                System.out.print("How much money would you like to deposit? ");
                double deposit = scan.nextDouble();
                scan.nextLine();
                savings.setCurrentBalance(deposit);
                transactionHistoryNum++;
                System.out.println("A000" + transactionHistoryNum + ". Deposited $" + deposit + " into savings account");
                TH.setTransactionHistory("A000" + transactionHistoryNum + ". Deposited $" + deposit + " into savings account\n");
            }
            if (account.equals("checking")) {
                System.out.print("How much money would you like to deposit? ");
                double deposit = scan.nextDouble();
                scan.nextLine();
                checking.setCurrentBalance(deposit);
                transactionHistoryNum++;
                System.out.println("A000" + transactionHistoryNum + ". Deposited $" + deposit + " into checking account");
                TH.setTransactionHistory("A000" + transactionHistoryNum + ". Deposited $" + deposit + " into checking account\n");
            }
            // returns to main menu
            accountActions();
        }
        if (choice == 3) {
            // transfers money between savings and checking accounts
            System.out.print("Which account would you like to transfer money to? (savings/checking)");
            String transferTo = scan.nextLine();
            if (transferTo.equals("savings")) {
                System.out.print("How much money would you like to transfer to savings? ");
                double transfer = scan.nextDouble();
                scan.nextLine();
                // checks if there are sufficient funds in checking to transfer the amount to savings
                if (transfer <= checking.getCurrentBalance()) {
                    savings.setCurrentBalance(transfer);
                    checking.setCurrentBalance(-transfer);
                    transactionHistoryNum++;
                    System.out.println("A000" + transactionHistoryNum + ". Transferred $" + transfer + " from checking account into savings account");
                    TH.setTransactionHistory("A000" + transactionHistoryNum + ". Transferred $" + transfer + " from checking account into savings account\n");
                } else {
                    System.out.println("It seems you do not have sufficient funds in your checking account to transfer $" + transfer + " to your savings account");
                    accountActions();
                }
            }
            if (transferTo.equals("checking")) {
                System.out.print("How much money would you like to transfer to checking? ");
                double transfer = scan.nextDouble();
                scan.nextLine();
                // checks if there are sufficient funds in savings to transfer the amount to checking
                if (transfer <= savings.getCurrentBalance()) {
                    checking.setCurrentBalance(transfer);
                    savings.setCurrentBalance(-transfer);
                    transactionHistoryNum++;
                    System.out.println("A000" + transactionHistoryNum + ". Transferred $" + transfer + " from savings account into checking account");
                    TH.setTransactionHistory("A000" + transactionHistoryNum + ". Transferred $" + transfer + " from savings account into checking account\n");
                } else {
                    System.out.println("It seems you do not have sufficient funds in your savings account to transfer $" + transfer + " to your checking account");
                    accountActions();
                }
            }
            accountActions();
        }
        if (choice == 4) {
            // prints current balances of savings and checking
            System.out.println("savings account balance: $" + savings.getCurrentBalance() + "\n" + "checking account balance: $" + checking.getCurrentBalance());
            transactionHistoryNum2++;
            System.out.println("S000" + transactionHistoryNum2 + ". Checked account balances of savings and checking accounts");
            TH.setTransactionHistory("S000" + transactionHistoryNum2 + ". Checked account balances of savings and checking accounts\n");
            accountActions();
        }
        if (choice == 5) {
            // prints transaction history
            System.out.println(TH.getTransactionHistory());
            transactionHistoryNum2++;
            System.out.println("S000" + transactionHistoryNum2 + ". Viewed transaction history");
            TH.setTransactionHistory("S000" + transactionHistoryNum2 + ". Viewed transaction history\n");
            accountActions();
        }
        if (choice == 6) {
            // changes PIN
            System.out.print("What is your current PIN? ");
            int currentPIN = scan.nextInt();
            scan.nextLine();
            if (currentPIN == customer.getPIN()) {
                System.out.print("What would you like your new PIN to be? ");
                int newPIN = scan.nextInt();
                scan.nextLine();
                customer.setPIN(newPIN);
                transactionHistoryNum2++;
                System.out.println("S000" + transactionHistoryNum2 + ". Changed PIN to " + newPIN);
                TH.setTransactionHistory("S000" + transactionHistoryNum2 + ". Changed PIN to " + newPIN + "\n");
            } else {
                System.out.println("That seems to be incorrect. \nPlease try again. ");
            }
            accountActions();
        }
        if (choice == 7) {
            // ends ATM and user interaction
            System.out.println("Thank you for banking with Aurum Fundamentals! We await your return " + userName + "!");
            System.exit(0);
        } else {
            accountActions();
        }
    }
}