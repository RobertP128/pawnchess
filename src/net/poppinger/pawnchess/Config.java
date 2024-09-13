package net.poppinger.pawnchess;

public class Config implements IConfig {
    public final int WIDTH=8;
    public final int HEIGHT=8;

    public final char FIGURE_PAWN_BLACK='p';
    public final char FIGURE_PAWN_WHITE='P';
    public final char FIGURE_EMPTY='E';

    @Override
    public int getWIDTH() {
        return WIDTH;
    }

    @Override
    public int getHEIGHT() {
        return HEIGHT;
    }

    @Override
    public char getFIGURE_PAWN_BLACK() {
        return FIGURE_PAWN_BLACK;
    }

    @Override
    public char getFIGURE_PAWN_WHITE() {
        return FIGURE_PAWN_WHITE;
    }

    @Override
    public char getFIGURE_EMPTY() {
        return FIGURE_EMPTY;
    }
}
