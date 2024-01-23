public class TransactionHistory {
    private String transactionHistory = "";

    // initializes transaction history
    public TransactionHistory(String transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    // gets transaction history
    public String getTransactionHistory() {
        return transactionHistory;
    }

    // edits transaction history
    public void setTransactionHistory(String str) {
        transactionHistory += str;
    }
}
