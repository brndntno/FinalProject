public class Customer {
    private String name;
    private int PIN;

    // initializes name and PIN
    public Customer(String name, int PIN) {
        this.name = name;
        this.PIN = PIN;
    }

    // gets PIN
    public int getPIN() {
        return PIN;
    }

    // changes PIN
    public void setPIN(int newPIN) {
        PIN = newPIN;
    }

}
