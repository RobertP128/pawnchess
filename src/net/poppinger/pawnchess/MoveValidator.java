package net.poppinger.pawnchess;

import jdk.jshell.spi.ExecutionControl;

import java.text.ParseException;

public class MoveValidator {

    IConfig config;

    public MoveValidator(IConfig config){
        this.config=config;
    }
    public MoveError validateMove(Board board,Move move,Player currentPlayer) throws MoveException {
        //throw new ExecutionControl.NotImplementedException("Not yet implemented");

        char FromField=board.getFieldByPos(move.fromPos);
        // check if fromPos matches current PLayers figure
        if (FromField!=currentPlayer.getCCode()) throw new MoveException("From field is not yours");

        // check if to Pos is empty
        char toField=board.getFieldByPos(move.toPos);
        if (toField!=config.getFIGURE_EMPTY()) {
            if (toField==currentPlayer.getCCode()) throw new MoveException("You cannot take your own Figure");
            if (currentPlayer==Player.ONE) {
                if ((move.fromPos.x + 1 == move.toPos.x && move.fromPos.y - 1 == move.toPos.y) ||
                        (move.fromPos.x - 1 == move.toPos.x && move.fromPos.y - 1 == move.toPos.y)) {
                    return MoveError.MOVE_TAKE_TARGET;
                } else {
                    throw new MoveException("Field is already occupied");
                }
            }
            else {
                if ((move.fromPos.x + 1 == move.toPos.x && move.fromPos.y + 1 == move.toPos.y) ||
                        (move.fromPos.x - 1 == move.toPos.x && move.fromPos.y + 1 == move.toPos.y)) {
                    return MoveError.MOVE_TAKE_TARGET;
                } else {
                    throw new MoveException("Field is already occupied");
                }
            }
        }
        // check if move is vertical and one step ahead

        if (move.fromPos.x!=move.toPos.x) throw new MoveException("Only vertical movements are allowed");
        if ((currentPlayer==Player.TWO &&  move.toPos.y>4)||(currentPlayer==Player.ONE &&  move.toPos.y<4)) {
            if (Math.abs(move.fromPos.y - move.toPos.y) != 1) throw new MoveException("Only one Step is allowed");
        }
        else {
            if (Math.abs(move.fromPos.y - move.toPos.y) > 2) throw new MoveException("Only max 2 Steps are allowed");
        }

        if (currentPlayer==Player.ONE) {
            if (move.fromPos.y + 1 == move.toPos.y ) {
                throw new MoveException("You cannot go backwards");
            }
        }
        else {
            if (move.fromPos.y - 1 == move.toPos.y ) {
                throw new MoveException("You cannot go backwards");
            }
        }

        return MoveError.MOVE_OK;
    }
}
