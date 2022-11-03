/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author 96654
 */
public class Transaction extends Service {
    private final Client client;
    private String message;

    public Transaction(Client client) {
        this.client = client;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            if (client.getLimit() >= amount) {
                client.setBalance(client.getBalance() + amount);
                message = "Deposit successful";
                return true;
            } else
                message = "Deposit failed. Limit exceeded";
            return false;
        }
        message = "Deposit failed";
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && client.getBalance() >= amount) {
            client.setBalance(client.getBalance() - amount);
            message = "Withdraw successful";
            return true;
        }
        message = "Withdraw failed";
        return false;
    }

    public boolean transfer(Client client1, double amount) {
        if (client1 != null && amount > 0 && client.getBalance() >= amount) {
            if (client1.getLimit() >= amount) {
                client.setBalance(client.getBalance() - amount);
                client1.setBalance(client1.getBalance() + amount);
                message = "Transfer successful";
                return true;
            } else
                message = "Transfer failed. Limit exceeded";
            return true;
        }
        message = "Transfer failed";
        return false;
    }

    public void printMessage(){
        System.out.println("------------------");
        System.out.println(message);
        System.out.println("Current Balance: " + client.getBalance());
        System.out.println("------------------");
    }
}