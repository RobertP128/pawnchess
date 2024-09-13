package net.poppinger.pawnchess;

import java.text.ParseException;

public class Pos {
    public int x;
    public int y;

    public static Pos fromString(String s) throws ParseException {
        if (s.isEmpty()) throw new ParseException("Missing input String",0);
        if (s.length()!=2) throw new ParseException("Illegal Length",0);
        if (s.charAt(0)<'A' || s.charAt(0)>'H') throw new ParseException("Illegal character",0);
        if (s.charAt(1)<'1' || s.charAt(1)>'8') throw new ParseException("Illegal character",1);

        var retval=new Pos();
        retval.x=s.charAt(0)-'A';
        retval.y=s.charAt(1)-'1';
        return retval;

    }
}
