/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author 96654
 */

import java.util.*;

public class Service {

    private Error error;
    private Client client;
    public void printMessage(){
        System.out.println(client.getBalance());
    }
    
    public boolean register(){
        client = new Client(0, "", 0, "", "", "", "", "");
        return true;
    }

    @Override
    public String toString() {
        return "Error: " + error.toString() + "\nClient: " + client.toString();
    }
    
    
    
}