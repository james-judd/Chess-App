class Pawn extends AbstractPiece {

    Pawn(boolean whiteBool){
        isWhite = whiteBool;
    }

    @Override public String toString(){
        if (isWhite){
            return("PAWN");
        }
        return("pawn");
    }

    boolean inRange(int[] start, int[] end){
        return (false);
    }
}