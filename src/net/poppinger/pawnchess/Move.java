package net.poppinger.pawnchess;

import java.text.ParseException;

public class Move {
    public Pos fromPos;
    public Pos toPos;
    public MoveError moveType;

    public static Move fromString(String s) throws ParseException {
        // Parse string
        var retval=new Move();
        if (s.length()<4) throw new ParseException("Message to short",0);
        retval.fromPos=Pos.fromString(s.substring(0,2));
        retval.toPos=Pos.fromString(s.substring(2,4));

        return retval;
    }
}
