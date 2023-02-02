class Bishop extends AbstractPiece {

    Bishop(boolean whiteBool){
        isWhite = whiteBool;
    }

    @Override public String toString(){
        if (isWhite){
            return("BISHOP");
        }
        return("bishop");
    }

    boolean canMoveToTarget(int[] start, int[] end, Board chessboard){
        if (Math.abs(end[0] - start[0]) == Math.abs(end[1] - start[1])){
            return (true);
        }
        return (false);
    }
}
