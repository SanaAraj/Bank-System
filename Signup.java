import java.util.ArrayList;

public class Signup extends Service {
    private int id;
    private String name;
    private String address;
    private String phone;
    private int age;
    private String PIN;
    private String username;
    private String password;
    private ArrayList<User> arrUsers;

    public Signup(ArrayList<User> users, int id, String name, String address, String phone, int age, String PIN, String username, String password) {
        this.arrUsers = users;
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.age = age;
        this.PIN = PIN;
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean register() {
        if (verifyRegister()) {
            arrUsers.add(new Client(id, name, age, address, phone, PIN, username, password));
            System.out.println("Client account registered. You can login now.");
            return true;
        }
        System.out.println("Client with given username already exists");
        return false;
    }   

    private boolean verifyRegister() {
        for (User user : arrUsers) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Id: " + id + "\nName: " + name + "\nAddress: " + address + "\nPhone: " + phone + "\nAge: " + age + "\nPIN: " + PIN + "\nUsername: " + username + "\nPassword: " + password;
    } 
    
}
