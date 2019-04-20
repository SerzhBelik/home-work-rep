package client;

public interface Controller {
    void sendMessage(String msg);

    void closeConnection();

    void showUI(ClientUI clientUI);
}
