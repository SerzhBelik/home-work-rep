package HomeWork7;

class User {
    private final String login;
    private final String password;
    private final String userName;
    private  boolean activate = true;

    User(String login, String password, String userName) {
        this.login = login;
        this.password = password;
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }
}

