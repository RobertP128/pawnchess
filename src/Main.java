import net.poppinger.pawnchess.*;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");
        Scanner scanner=null;


        ServerSocket server = new ServerSocket(2048);

        var socketPlayer1=server.accept();
        var is1=socketPlayer1.getInputStream();
        var os1 = new PrintStream(socketPlayer1.getOutputStream());
        os1.println("Hello. You are Player ONE. Waiting for Player Two......");

        var socketPlayer2=server.accept();
        var is2=socketPlayer2.getInputStream();
        var os2 = new PrintStream(socketPlayer2.getOutputStream());
        os2.println("Hello. You are Player TWO. Player ONE has already joined. Lets Go");


/*
        var is1=System.in;
        var os1 = System.out;
        var is2=System.in;
        var os2 = System.out;
        scanner=new Scanner(is1);
*/

        Config config=new Config();
        Board board=new Board(config);
        MoveValidator mv=new MoveValidator(config);

        var p1input = new UserInput(is1,os1,board,mv,config,scanner);
        var p2input = new UserInput(is2,os2,board,mv,config,scanner);

        Application app=new Application(p1input,p2input,board,config);
        //app.printBoard();
        app.run();

    }
}