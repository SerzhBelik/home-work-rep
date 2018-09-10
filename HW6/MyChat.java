package HomeWork6;

public class MyChat {

    public static void main(String[] args){
        Controller controller = new ClientController();
        ClientUI clientUI = new ChatWindow(controller);
        controller.showUI(clientUI);
    }
}
