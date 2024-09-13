package net.poppinger.pawnchess;

public enum Player {
    ONE('P'),
    TWO('p');

    char ccode;
    Player(char ccode){
        this.ccode=ccode;
    }

    public char getCCode(){
        return ccode;
    }
}
