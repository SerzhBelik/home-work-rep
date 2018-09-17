package HomeWork7;

public interface AuthService {
    String authByloginAndPassword(String login, String password);
    User createOrActivateUser (String login, String pass, String nick);
    boolean deactivateUser(String nick);
}
