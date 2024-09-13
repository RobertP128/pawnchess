package net.poppinger.pawnchess;

public class Board {

    private char[][] board;
    private IConfig config;

    public Board(IConfig config){
        board = new char[8][8];
        this.config=config;
    }

    public char[][] getBoard(){
        return board;
    }

    public char getFieldByPos(Pos pos){
        return board[pos.y][pos.x];
    }

    public void makeMove(Move move){
        char f=getFieldByPos(move.fromPos);
        board[move.toPos.y][move.toPos.x]=f;
        board[move.fromPos.y][move.fromPos.x]=config.getFIGURE_EMPTY();

    }

    public Player checkWinner(){
        boolean foundPlayer1=false;
        boolean foundPlayer2=false;
        for (int y=0;y<config.getHEIGHT();y++){
            for (int x=0;x<config.getWIDTH();x++){
                if (board[y][x]==Player.ONE.ccode) foundPlayer1=true;
                if (board[y][x]==Player.TWO.ccode) foundPlayer2=true;
            }
        }
        if (foundPlayer1 && !foundPlayer2) return Player.ONE;
        if (!foundPlayer1 && foundPlayer2) return Player.TWO;
        return null;
    }

    public void initBoard(){

        for (int y=0;y<config.getHEIGHT();y++){
            for (int x=0;x<config.getWIDTH();x++){
                if (y==0){
                    board[y][x] = config.getFIGURE_PAWN_BLACK();
                }
                else if (y==config.getHEIGHT()-1){
                    board[y][x] = config.getFIGURE_PAWN_WHITE();
                }
                else {
                    board[y][x] = config.getFIGURE_EMPTY();
                }
            }
        }
    }

}
