import java.util.Scanner;
public class ATM {
    String userName;
    int userPIN;
    public void start() {
        Scanner scan = new Scanner(System.in);




        System.out.println("Welcome user!");
        System.out.print("What would you like the name for your account to be? ");
        userName = scan.nextLine();
        System.out.print("Please enter a PIN for your new account: ");
        userPIN = scan.nextInt();




        Customer customer = new Customer(userName, userPIN);
        Account savings = new Account("savings", customer);
        Account checking = new Account("checking", customer);




        System.out.print("What is your PIN? ");
        if (scan.nextInt() == userPIN) {
            System.out.println("1. Withdraw money\n" +
                    "2. Deposit money\n" +
                    "3. Transfer money between accounts\n" +
                    "4. Get account balances\n" +
                    "5. Get transaction history\n" +
                    "6. Change PIN\n" +
                    "7. Exit\n");


            if (scan.nextInt() == 1) {
                System.out.print("Which account would you like to withdraw money from? ");
                if (scan.nextLine().equals("savings")) {
                    System.out.print("How much money would you like to withdraw? You can only withdraw $5s and $20s and do not include $ in your answer. ");
                    int withdraw = scan.nextInt();
                    if (withdraw % 5 == 0) {
                        System.out.print("How many $5s would you like? ");
                        int numFives = scan.nextInt();
                        System.out.print("How many $20s would you like? ");
                        int num20s = scan.nextInt();
                        if (numFives * 5 + num20s * 20 == withdraw) {
                            savings.setCurrentBalance(-scan.nextDouble());
                        }
                    }
                }
                if (scan.nextLine().equals("checking")) {
                    System.out.print("How much money would you like to withdraw? You can only withdraw $5s and $20s and do not include $ in your answer. ");
                    int withdraw = scan.nextInt();
                    if (withdraw % 5 == 0) {
                        System.out.print("How many $5s would you like? ");
                        int numFives = scan.nextInt();
                        System.out.print("How many $20s would you like? ");
                        int num20s = scan.nextInt();
                        if (numFives * 5 + num20s * 20 == withdraw) {
                            checking.setCurrentBalance(-scan.nextDouble());
                        }
                    }
                }
            }
            if (scan.nextInt() == 2) {
                System.out.print("Which account would you like to deposit money into? ");
                if (scan.nextLine().equals("savings")) {
                    System.out.print("How much money would you like to deposit? ");
                    savings.setCurrentBalance(scan.nextDouble());
                }
                if (scan.nextLine().equals("checking")) {
                    System.out.print("How much money would you like to deposit? ");
                    checking.setCurrentBalance(scan.nextDouble());
                }
            }
            if (scan.nextInt() == 3) {
                System.out.println("Which account would you like to transfer money to? ");
                if (scan.nextLine().equals("savings")) {
                    System.out.print("How much money would you like to transfer to savings? ");
                    double transfer = scan.nextDouble();
                    if (transfer <= checking.getCurrentBalance()) {
                        savings.setCurrentBalance(scan.nextDouble());
                        checking.setCurrentBalance(-scan.nextDouble());
                    }
                }
                if (scan.nextLine().equals("checking")) {
                    System.out.print("How much money would you like to transfer to checking? ");
                    double transfer = scan.nextDouble();
                    if (transfer <= savings.getCurrentBalance()) {
                        checking.setCurrentBalance(scan.nextDouble());
                        savings.setCurrentBalance(-scan.nextDouble());
                    }
                }
            }
            if (scan.nextInt() == 4) {
                System.out.println("savings account balance: $" + savings.getCurrentBalance() + "\n" +
                        "checking account balance: $" + checking.getCurrentBalance());
            }
            if (scan.nextInt() == 5) {

            }
        } else {
            System.out.println("That is incorrect");
        }
    }
}


