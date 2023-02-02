class Pawn extends AbstractPiece {

    Pawn(boolean whiteBool){
        isWhite = whiteBool;
    }

    @Override public String toString(){
        if (isWhite){
            return("PAWN");
        }
        else{
            return("pawn");
        }
    }

    boolean canMoveToTarget(int[] start, int[] end, Board chessboard){
        boolean isWhite = chessboard.board[start[0]][start[1]].isWhite;
        if (start[1] == end[1]){
            int startRow;
            int move;
            if (isWhite == true){
                startRow = 1;
                move = 1;
            }
            else {
                startRow = 7;
                move = -1;
            }
            if (end[0] == start[0] + move){
                return (true);
            }
            if (start[0] == startRow && end[0] == start[0] + 2 * move){
                return (true);
            }
        }
        return (false);
    }
}