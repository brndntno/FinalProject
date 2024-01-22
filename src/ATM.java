import java.util.Scanner;
public class ATM {
    private String userName;
    private int userPIN;
    private int transactionHistoryNum = 0;
    private int transactionHistoryNum2 = 0;
    private Account savings;
    private Account checking;
    private Scanner scan;

    public void start() {
        accountInfo();
    }
    public void accountInfo() {
        scan = new Scanner(System.in);

        System.out.println("Welcome user!");
        System.out.print("What would you like the name for your account to be? ");
        userName = scan.nextLine();
        System.out.print("Please enter a PIN for your new account: ");
        userPIN = scan.nextInt();

        Customer customer = new Customer(userName, userPIN);

        System.out.print("What is your PIN? ");
        int PIN = scan.nextInt();
        if (PIN == customer.getPIN()) {
            accountActions();
        } else {
            System.out.println("That is incorrect");
            accountInfo();
        }
    }
    public void accountActions() {
        scan = new Scanner(System.in);
        Customer customer = new Customer(userName, userPIN);
        savings = new Account("savings", customer);
        checking = new Account("checking", customer);
        TransactionHistory TH = new TransactionHistory("");

        System.out.println("1. Withdraw money\n" +
                "2. Deposit money\n" +
                "3. Transfer money between accounts\n" +
                "4. Get account balances\n" +
                "5. Get transaction history\n" +
                "6. Change PIN\n" +
                "7. Exit\n");

        System.out.print("Enter a number: ");
        int choice = scan.nextInt();
        if (choice == 1) {
            System.out.print("Which account would you like to withdraw money from? ");
            String account = scan.nextLine();
            if (account.equals("savings")) {
                System.out.print("How much money would you like to withdraw? You can only withdraw $5s and $20s and do not include $ in your answer. ");
                int withdraw = scan.nextInt();
                if (withdraw <= savings.getCurrentBalance() && withdraw % 5 == 0) {
                    System.out.print("How many $5s would you like? ");
                    int numFives = scan.nextInt();
                    System.out.print("How many $20s would you like? ");
                    int num20s = scan.nextInt();
                    if (numFives * 5 + num20s * 20 == withdraw) {
                        savings.setCurrentBalance(-withdraw);
                        transactionHistoryNum++;
                        TH.setTransactionHistory("A000" + transactionHistoryNum + ". Withdrew $" + withdraw + " from savings account\n");
                        accountActions();
                    }
                }
            }
            if (account.equals("checking")) {
                System.out.print("How much money would you like to withdraw? You can only withdraw $5s and $20s and do not include $ in your answer. ");
                int withdraw = scan.nextInt();
                if (withdraw <= checking.getCurrentBalance() && withdraw % 5 == 0) {
                    System.out.print("How many $5s would you like? ");
                    int numFives = scan.nextInt();
                    System.out.print("How many $20s would you like? ");
                    int num20s = scan.nextInt();
                    if (numFives * 5 + num20s * 20 == withdraw) {
                        checking.setCurrentBalance(-withdraw);
                        transactionHistoryNum++;
                        TH.setTransactionHistory("A000" + transactionHistoryNum + ". Withdrew $" + withdraw + " from checking account\n");
                        accountActions();
                    }
                }
            }
        }
        if (choice == 2) {
            System.out.print("Which account would you like to deposit money into? ");
            String account = scan.nextLine();
            if (account.equals("savings")) {
                System.out.print("How much money would you like to deposit? ");
                double deposit = scan.nextDouble();
                savings.setCurrentBalance(deposit);
                transactionHistoryNum++;
                TH.setTransactionHistory("A000" + transactionHistoryNum + ". Deposited $" + deposit + " into savings account\n");
            }
            if (account.equals("checking")) {
                System.out.print("How much money would you like to deposit? ");
                double deposit = scan.nextDouble();
                checking.setCurrentBalance(deposit);
                transactionHistoryNum++;
                TH.setTransactionHistory("A000" + transactionHistoryNum + ". Deposited $" + deposit + " into savings account\n");
            }
            accountActions();
        }
        if (choice == 3) {
            System.out.println("Which account would you like to transfer money to? ");
            String transferTo = scan.nextLine();
            if (transferTo.equals("savings")) {
                System.out.print("How much money would you like to transfer to savings? ");
                double transfer = scan.nextDouble();
                if (transfer <= checking.getCurrentBalance()) {
                    savings.setCurrentBalance(transfer);
                    checking.setCurrentBalance(-transfer);
                    transactionHistoryNum++;
                    TH.setTransactionHistory("A000" + transactionHistoryNum + ". Transferred $" + transfer + " from checking account into savings account\n");
                }
            }
            if (transferTo.equals("checking")) {
                System.out.print("How much money would you like to transfer to checking? ");
                double transfer = scan.nextDouble();
                if (transfer <= savings.getCurrentBalance()) {
                    checking.setCurrentBalance(transfer);
                    savings.setCurrentBalance(-transfer);
                    transactionHistoryNum++;
                    TH.setTransactionHistory("A000" + transactionHistoryNum + ". Transferred $" + transfer + " from savings account into checking account\n");
                }
            }
            accountActions();
        }
        if (choice == 4) {
            System.out.println("savings account balance: $" + savings.getCurrentBalance() + "\n" + "checking account balance: $" + checking.getCurrentBalance());
            transactionHistoryNum2++;
            TH.setTransactionHistory("S000" + transactionHistoryNum2 + ". Checked account balances of savings and checking accounts\n");
            accountActions();
        }
        if (choice == 5) {
            System.out.println(TH.getTransactionHistory());
            transactionHistoryNum2++;
            TH.setTransactionHistory("S000" + transactionHistoryNum2 + ". Viewed transaction history");
            accountActions();
        }
        if (choice == 6) {
            System.out.print("What is your current PIN? ");
            int currentPIN = scan.nextInt();
            if (currentPIN == customer.getPIN()) {
                System.out.print("What would you like your new PIN to be? ");
                int newPIN = scan.nextInt();
                customer.setPIN(newPIN);
                transactionHistoryNum2++;
                TH.setTransactionHistory("S000" + transactionHistoryNum2 + ". Changed PIN to " + newPIN + "\n");
            }
            accountActions();
        }
        if (choice == 7) {
            System.out.println("Thank you for banking with us!");
            System.exit(0);
        } else {
            accountActions();
        }
    }
}




