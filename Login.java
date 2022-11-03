import java.util.ArrayList;

public class Login extends Service {
    private String username, password;
    private int id;
    private ArrayList<User> arrUsers;

    public Login(ArrayList<User> users, String username, String password) {
        this.arrUsers = users;
        this.username = username;
        this.password = password;
    }

    
        public Login(ArrayList<User> users, int id, String password) {
        this.arrUsers = users;
        this.id = id;
        this.password = password;
    }

    public User validate(){
        for (User user : arrUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    
        public User validation(){
        for (User user : arrUsers) {
            if (user.getId().equals(id) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
}