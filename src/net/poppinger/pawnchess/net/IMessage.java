package net.poppinger.pawnchess.net;

import java.net.Socket;

public interface IMessage {
    void onReceiveMessage(String msg, Socket sender);
}
