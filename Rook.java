class Rook extends AbstractPiece {

    Rook(boolean whiteBool){
        isWhite = whiteBool;
    }

    @Override public String toString(){
        if (isWhite){
            return("ROOK");
        }
        else{
            return("rook");
        }
    }

    boolean inRange(int[] start, int[] end){
        if (start[0] != end[0] || start[1] != end[1]){
            if (start[0] == end[0] || start[1] == end[1]){
                return (true);
            }
        }
        return (false);
    }
}