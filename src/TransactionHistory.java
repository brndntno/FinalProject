public class TransactionHistory {
    private String transactionHistory = "";

    public TransactionHistory(String transactionHistory) {
        this.transactionHistory.equals(transactionHistory);
    }

    public String getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(String str) {
        transactionHistory += str;
    }
}
