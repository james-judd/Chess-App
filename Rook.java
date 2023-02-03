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

    boolean canMoveToTarget(int[] start, int[] end, Board chessboard){
        if (start[0] == end[0] || start[1] == end[1]){
            if (chessboard.board[end[0]][end[1]] != null){
                if (chessboard.board[end[0]][end[1]].isWhite == isWhite){
                    return (false);
                }
            }
            return (true);
        }
        return (false);
    }
}
