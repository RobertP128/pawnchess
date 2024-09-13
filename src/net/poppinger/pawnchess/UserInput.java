package net.poppinger.pawnchess;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.Scanner;

public class UserInput {

    private InputStream inStream;
    private PrintStream outStream;

    private MoveValidator moveValidator;
    private Scanner scanner;
    private Board board;
    private IConfig config;

    public UserInput(InputStream inStream, PrintStream outStream,Board board,MoveValidator moveValidator,IConfig config,Scanner extScanner){
        this.inStream=inStream;
        this.outStream=outStream;
        this.moveValidator=moveValidator;
        this.board=board;
        this.config=config;

        scanner=(extScanner==null) ?  new Scanner(this.inStream) : extScanner;
    }

    public PrintStream getOutStream(){
        return outStream;
    }

    public Move requestMove(Player player){
        boolean moveIsValid=false;
        Move move=null;
        do {
            outStream.println("Wählen Sie Ihren Zug Player" + player);
            var moveStr = scanner.next();

            try {
                move = Move.fromString(moveStr);

                // Validate Move
                MoveError error = moveValidator.validateMove(board, move, player);
                if (error != MoveError.MOVE_OK && error!=MoveError.MOVE_TAKE_TARGET) {
                    outStream.println(error);

                } else {
                    moveIsValid = true;
                }
                move.moveType=error;
            }
            catch (ParseException e){
                outStream.println(e.getMessage());
            }
            catch (MoveException e){
                outStream.println(e.getMessage());
            }
        }while(!moveIsValid);

        return move;
    }


    public void printBoard(){
        outStream.print(" |");
        for (int x=0;x<config.getWIDTH();x++){
            outStream.print((char)(x+'A')+"|");
        }
        outStream.println("  <--- Player TWO");

        for (int y=0;y<config.getHEIGHT();y++){
            for (int x=0;x<config.getWIDTH();x++){
                if (x==0){
                    outStream.print((y+1)+"|");
                }

                if (board.getBoard()[y][x]==config.getFIGURE_PAWN_BLACK()) {
                    outStream.print("X|");
                }
                else if (board.getBoard()[y][x]==config.getFIGURE_PAWN_WHITE()) {
                    outStream.print("O|");
                }
                else {
                    outStream.print(" |");
                }
            }

            if (y< config.getHEIGHT()-1) outStream.println();
        }
        outStream.println("  <--- Player ONE");

    }

    public void printOtherPlayersTurn(Player currentPlayer){
        outStream.println("Its Player" + currentPlayer+"'s turn now");
        outStream.flush();

    }

    public void printWinner(Player winner){
        outStream.println("The winner is Player" + winner+" !!!!!!");
        outStream.flush();

    }
}
