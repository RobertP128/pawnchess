import net.poppinger.pawnchess.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        ServerSocket server = new ServerSocket(2048);

        var sockerPlayer1=server.accept();
        var is1=sockerPlayer1.getInputStream();
        var os1 = new PrintStream(sockerPlayer1.getOutputStream());
        os1.println("Hello. You are Plyer ONE. Waiting for Player Two......");

        var sockerPlayer2=server.accept();
        var is2=sockerPlayer2.getInputStream();
        var os2 = new PrintStream(sockerPlayer2.getOutputStream());
        os2.println("Hello. You are Plyer TWO. Player ONE has already joined. Lets Go");

/*
        var is1=System.in;
        var os1 = System.out;
        var is2=System.in;
        var os2 = System.out;
*/

        Config config=new Config();
        Board board=new Board(config);
        MoveValidator mv=new MoveValidator(config);
        var p1intput = new UserInput(is1,os1,board,mv,config);
        var p2intput = new UserInput(is2,os2,board,mv,config);

        Application app=new Application(p1intput,p2intput,board,config);
        //app.printBoard();
        app.run();

    }
}