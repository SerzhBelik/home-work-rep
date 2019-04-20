package server;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    private List<User> users = new ArrayList<>();
    public BaseAuthService(){
        users.add(new User("login1", "pass1", "user1"));
        users.add(new User("login2", "pass2", "user2"));
        users.add(new User("login3", "pass3", "user3"));
    }
    @Override
    public String authByloginAndPassword(String login, String password) {
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
                return user.getUserName();
            }
        }
        return null;
    }
}