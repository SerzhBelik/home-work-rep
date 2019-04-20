package client;

import java.nio.channels.SelectionKey;

public interface ClientUI {
    void showUI();

    void addMessage(String w);
    void addMessage(SelectionKey key);
}
