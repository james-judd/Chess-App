class King extends AbstractPiece {

    King(boolean whiteBool){
        isWhite = whiteBool;
    }

    @Override public String toString(){
        if (isWhite){
            return("KING");
        }
        return("king");
    }

    boolean canMoveToTarget(int[] start, int[] end, Board chessboard){
        System.out.println(Math.abs(end[0] - start[0]));
        System.out.println(Math.abs(end[1] - start[1]));
        if (Math.abs(end[0] - start[0]) <= 1 && Math.abs(end[1] - start[1]) <= 1){
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
