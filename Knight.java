class Knight extends AbstractPiece {

    Knight(boolean whiteBool){
        isWhite = whiteBool;
    }

    @Override public String toString(){
        if (isWhite){
            return("KNIGHT");
        }
        return("knight");
    }

    boolean inRange(int[] start, int[] end){
        if (start[0] != end[0] && start[1] != end[1] && Math.abs(end[0] - start[0]) + Math.abs(end[1] - start[1]) == 3){
            return (true);
        }
        return (false);
    }
}