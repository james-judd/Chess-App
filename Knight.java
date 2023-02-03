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

    boolean canMoveToTarget(int[] start, int[] end, Board chessboard){
        if (start[0] != end[0] && start[1] != end[1] && Math.abs(end[0] - start[0]) + Math.abs(end[1] - start[1]) == 3){
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
