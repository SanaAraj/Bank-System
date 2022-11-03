import java.util.ArrayList;

public class Admin extends User {
    private int id;

    private ArrayList<Client> clients = new ArrayList<Client>();

    public Admin(int id, String name, String contactNum, int age, String username, String password) {
        super(name, contactNum, age, username, password);
        this.id = id;
    }

    public boolean close_Client_Acc(int userID) {
        for (Client client : clients) {
            if (client.getId() == userID) {
                client.closeAcc();
                System.out.println("Account closed");
                return true;
            }
        }
        System.out.println("Account not found");
        return false;
    }


    public void delete_Client_Acc(int userID) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == userID) {
                clients.remove(i);
                System.out.println("Deleted Successfully");
                return;
            }
        }
        System.out.println("User not found");
    }

    public void print_Client_Acc_Info() {
        if (clients.size() == 0) {
            System.out.println("No clients");
            return;
        }
        System.out.printf("%-10s%-5s%-10s%-16s%-16s%-10s%-10s%-10s%-10s%n", "Name", "ID", "Address", "Contact Number", "Account Number", "Username", "Balance", "Limit", "Is Closed");
        for (Client client : clients) {
            System.out.printf("%-10s%-5s%-10s%-16s%-16s%-10s%-10s%-10s%-10s%n", client.getName(), client.getId(), client.getAddress(), client.getContactNum(), client.getAccNum(), client.getUsername(), client.getBalance(), client.getLimit(), client.isClosed());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }
}
