class Queen extends AbstractPiece {

    Queen(boolean whiteBool){
        isWhite = whiteBool;
    }

    @Override public String toString(){
        if (isWhite){
            return("QUEEN");
        }
        return("queen");
    }

    boolean inRange(int[] start, int[] end){
        if (start[0] != end[0] || start[1] != end[1]){
            if (start[0] == end[0] || start[1] == end[1] || Math.abs(end[0] - start[0]) == Math.abs(end[1] - start[1])){
                return (true);
            }
        }
        return (false);
    }
}