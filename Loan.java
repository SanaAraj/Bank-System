/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;

/**
 * @author 96654
 */
public class Loan extends Service {
    private Client client;
    private String message;

    public Loan(Client client) {
        this.client = client;
    }

    public void loan(double amount) {
        client.setBalance(client.getBalance() + amount);
        message = "Loan successful";
    }

    public void printLoanMessage(){
        System.out.println("------------------");
        System.out.println(message);
        System.out.println("Current Balance: " + client.getBalance());
        System.out.println("------------------");
    }
   
}
