package HomeWork6;

public class MyChat {
    protected static void main(String[] args){
        Conroller conroller = new ClientController;
        ClientUI clientUI = new Client(controller);
        controller.showUI(clientUI);
    }
}
