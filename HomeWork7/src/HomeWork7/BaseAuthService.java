package HomeWork7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseAuthService implements AuthService {
    private Map<String, User> users = new HashMap<>();
    public BaseAuthService(){
        users.put("nick1",new User("login1", "pass1", "user1"));
        users.put("nick2",new User("login2", "pass2", "user2"));
        users.put("nick3",new User("login3", "pass3", "user3"));
    }
    @Override
    public String authByloginAndPassword(String login, String password) {
        for (User user : users.values()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password) && user.isActivate()){
                return user.getUserName();
            }
        }
        return null;
    }

    @Override
    public User createOrActivateUser(String login, String pass, String nick){
        User user = new User(login, pass, nick);
        if (users.containsKey(nick)){
            users.get(nick).setActivate(true);
            System.out.println("User with nick " + nick + " is already exist!");
        } else {
            users.put(nick, user);
        }
        return user;
    }

    @Override
    public boolean deactivateUser(String nick) {
        User user = users.get(nick);
        if (user != null){
            user.setActivate(false);
            return true;
        }
        return false;
    }
}
