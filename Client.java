import java.util.ArrayList;

public class Client extends User {
    private int id;
    private String address;
    private String accNum;
    private static String accountNumber = "A000000000";
    private String PIN;
    private double limit;
    private boolean isClosed = false;
    private double balance = 0;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();

    public Client(int id, String name, int age, String address, String contactNum, String PIN, String username,
            String password) {
        super(name, contactNum, age, id, username, password);
        // this.id = id;
        this.name = name;
        this.address = address;
        this.PIN = PIN;
        this.accNum = accountNumber;
        limit = 5000;
        accountNumber = incrementAccountNumber(accountNumber);
    }

    public void closeAcc() {
        isClosed = true;
    }

    public int getId() {
        return id;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public void view_Client_Acc() {
        System.out.printf("%-10s%-5s%-10s%-16s%-16s%-10s%-10s%-10s%-10s%n", "Name", "ID", "Address", "Contact Number",
                "Account Number", "Username", "Balance", "Limit", "Is Closed");
        System.out.printf("%-10s%-5s%-10s%-16s%-16s%-10s%-10s%-10s%-10s%n", name, id, address, contactNum, accNum,
                username, balance, limit, isClosed);
    }

    public void print_history() {
        int i = 0;
        for (Transaction transaction : transactions) {
            System.out.println("Transaction " + i);
            transaction.printMessage();
            i++;
        }
    }

    public void print_loans() {
        int i = 0;
        for (Loan loan : loans) {
            System.out.println("Loan " + i);
            loan.printLoanMessage();
            i++;
        }
    }

    public void changeLimit(double limit) {
        this.limit = limit;
    }

    private String incrementAccountNumber(String accountNumber) {
        String newAccountNumber = "";
        int number = Integer.parseInt(accountNumber.substring(1));
        number++;
        newAccountNumber = "A" + String.format("%09d", number);
        return newAccountNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public double getLimit() {
        return limit;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }
}
