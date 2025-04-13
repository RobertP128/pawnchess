package net.poppinger.pawnchess.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class AcceptThread extends Thread{

    IMessage callback;
    Socket socket;
    InputStream is;

    public AcceptThread(IMessage callback, Socket socket){
        this.callback=callback;
        this.socket=socket;
    }

    @Override
    public void run(){


        try {

            var scanner=new Scanner(socket.getInputStream());
            while (true) {
                var cmd=scanner.next();
                callback.onReceiveMessage(cmd,socket);
            }
        }
        catch (IOException e){

        }
        finally {
            // closing socket
            try {
                socket.close();
            }
            catch (IOException e){
                // ok anyway
            }
        }

    }
}
