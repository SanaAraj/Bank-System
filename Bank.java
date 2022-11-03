import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    private static final Scanner in = new Scanner(System.in);
    private static final Admin ADMIN = new Admin(1, "Admin", "0123456789", 20, "admin", "admin");
    private static Service loginService;
    private static Service signupService;
    private static Service loanService;
    private static ArrayList<User> users;

    public static void main(String[] args) {
        users = new ArrayList<>();
        users.add(ADMIN);

        users.add(new Client(2, "Client", 19, "Dammam", "123456987", "1234", "client", "client"));
        users.add(new Client(3, "Client2", 20, "Riyadh", "987563214", "5741", "client2", "client2"));

        setClientsToAdmin();

        while (true) {
            authMenu();
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 1) {
                User c = login();
                if (c != null) {
                    if (c instanceof Client) {
                        clientMenuController((Client) c);
                    } else System.out.println("Invalid username or password");
                } else System.out.println("Invalid username or password");
            } else if (choice == 2) {
                if (login() != null)
                    adminMenuController();
                else System.out.println("Invalid admin name or password");
            } else if (choice == 3) {
                signup();
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void authMenu() {
    	System.out.println("Welcome to JAVA ATM");
        System.out.println("1. Login as Client");
        System.out.println("2. Login as Admin");
        System.out.println("3. Signup");
        System.out.println("4. Exit");
    }

    public static void adminMenu() {
        System.out.println("1. Close Client Account");
        System.out.println("2. Delete Client Account");
        System.out.println("3. Print Client Account Info");
        System.out.println("4. Exit");
    }

    public static void clientMenu() {
        System.out.println("1. Apply for Loan");
        System.out.println("2. View Loan Info");
        System.out.println("3. View Account Info");
        System.out.println("4. Deposit");
        System.out.println("5. Withdraw");
        System.out.println("6. Transfer");
        System.out.println("7. Print Transaction History");
        System.out.println("8. Set Limit");
        System.out.println("9. Exit");
    }

    public static void clientMenuController(Client client) {
        while (true) {
            clientMenu();
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 1) {
                Loan loan = new Loan(client);
                System.out.print("Enter amount: ");
                double amount = Double.parseDouble(in.nextLine());
                loan.loan(amount);
                client.getLoans().add(loan);
                loan.printLoanMessage();
            } else if (choice == 2) {
                client.print_loans();
            } else if (choice == 3) {
                client.view_Client_Acc();
            }
            else if (choice == 4 || choice==5 || choice == 6){
                Transaction transaction = new Transaction(client);
                System.out.print("Enter PIN: ");
                String pin = in.nextLine();
                if (!pin.equals(client.getPIN())) {
                    System.out.println("Invalid PIN");
                    continue;
                }
                if (choice == 4){
                    System.out.print("Enter amount: ");
                    double amount = Double.parseDouble(in.nextLine());
                    if (transaction.deposit(amount))
                        client.getTransactions().add(transaction);
                    transaction.printMessage();
                }
                else if (choice == 5){
                    System.out.print("Enter amount: ");
                    double amount = Double.parseDouble(in.nextLine());
                    if (transaction.withdraw(amount))
                        client.getTransactions().add(transaction);
                    transaction.printMessage();
                }
                else {
                    System.out.print("Enter amount: ");
                    double amount = Double.parseDouble(in.nextLine());
                    System.out.print("Enter account number: ");
                    String accountNumber = in.nextLine();
                    Client receiver = getClientByAccountNumber(accountNumber);
                    if (receiver != null) {
                        if (transaction.transfer(receiver, amount))
                            client.getTransactions().add(transaction);
                        transaction.printMessage();
                    } else System.out.println("Invalid account number");
                }
            }
             else if (choice == 7) {
                client.print_history();
            }
             else if (choice == 8) {
                System.out.print("Enter PIN: ");
                String pin = in.nextLine();
                if (!pin.equals(client.getPIN())) {
                    System.out.println("Invalid PIN");
                    continue;
                }
                System.out.print("Enter limit: ");
                double limit = Double.parseDouble(in.nextLine());
                client.changeLimit(limit);
                System.out.println("Limit set successfully");
            }
             else if (choice == 9) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static void adminMenuController() {
        while (true) {
            adminMenu();
            int choice = Integer.parseInt(in.nextLine());
            if (choice == 1) {
                System.out.print("Enter Client ID: ");
                int id = Integer.parseInt(in.nextLine());
                ADMIN.close_Client_Acc(id);
            } else if (choice == 2) {
                System.out.print("Enter Client ID: ");
                int id = Integer.parseInt(in.nextLine());
                ADMIN.delete_Client_Acc(id);
            } else if (choice == 3) {
                ADMIN.print_Client_Acc_Info();
            } else if (choice == 4) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    public static User login() {
        System.out.println("Enter username: ");
        String username = in.nextLine();
        System.out.println("Enter password: ");
        String password = in.nextLine();

        loginService = new Login(users, username, password);
        return ((Login) loginService).validate();
    }

    public static boolean signup() {
        System.out.println("Enter username: ");
        String username = in.nextLine();
        System.out.println("Enter password: ");
        String password = in.nextLine();
        System.out.println("Enter name: ");
        String name = in.nextLine();
        System.out.println("Enter address: ");
        String address = in.nextLine();
        System.out.println("Enter phone: ");
        String phone = in.nextLine();
        System.out.println("Enter age: ");
        int age = Integer.parseInt(in.nextLine());
        System.out.println("Enter PIN: ");
        String PIN = in.nextLine();

        signupService = new Signup(users, users.size() + 1, name, address, phone, age, PIN, username, password);
        return ((Signup) signupService).register();
    }

    public static void setClientsToAdmin() {
        ArrayList<Client> clients = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Client)
                clients.add((Client) user);
        }
        ADMIN.setClients(clients);
    }

    public static Client getClientByAccountNumber(String accountNumber) {
        for (Client client : ADMIN.getClients()) {
            if (client.getAccNum().equals(accountNumber))
                return client;
        }
        return null;
    }
}