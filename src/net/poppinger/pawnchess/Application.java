package net.poppinger.pawnchess;

import java.io.InputStream;
import java.io.PrintStream;

public class Application {



    Board board ;


    private UserInput player1Input;
    private UserInput player2Input;

    private Player currentPlayer;
    private IConfig config;

    public Application(UserInput player1Input,UserInput player2Input,Board board,IConfig config ){
        this.board=board;
        this.player1Input=player1Input;
        this.player2Input=player2Input;
        this.config=config;
        currentPlayer=Player.ONE;
        board.initBoard();
    }




    private Player getInversCurrentPlayer(){
        return (currentPlayer==Player.ONE) ? Player.TWO : Player.ONE;
    }

    private UserInput getUserInputByCurrentPlayer(){
        return (currentPlayer==Player.ONE) ? player1Input : player2Input;
    }
    private UserInput getUserInputByInversCurrentPlayer(){
        return (currentPlayer==Player.ONE) ? player2Input : player1Input;
    }

    public void run() throws Exception{
        player1Input.printBoard();
        if (player2Input.getOutStream()!=player1Input.getOutStream()) player2Input.printBoard();
        boolean gameEnded=false;
        while (!gameEnded) {
            getUserInputByInversCurrentPlayer().printOtherPlayersTurn(currentPlayer);
            Move m = getUserInputByCurrentPlayer().requestMove(currentPlayer);

            board.makeMove(m);
            player1Input.printBoard();
            Player winner=board.checkWinner();
            if (winner==null) {
                if (player2Input.getOutStream() != player1Input.getOutStream()) player2Input.printBoard();
                swapPlayer();
            }
            else {
                player1Input.printWinner(winner);
                player2Input.printWinner(winner);
                gameEnded=true;
            }


        }
    }

    private void swapPlayer(){
        currentPlayer= (currentPlayer==Player.ONE) ? Player.TWO : Player.ONE;
    }


}
