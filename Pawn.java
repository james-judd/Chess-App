class Pawn extends AbstractPiece {

    Pawn(boolean whiteBool){
        isWhite = whiteBool;
        doubleMoveTurn = -1;
    }

    @Override public String toString(){
        if (isWhite){
            return("PAWN");
        }
        return("pawn");
    }

    boolean canMoveToTarget(int[] start, int[] end, Board chessboard){
        boolean isWhite = chessboard.board[start[0]][start[1]].isWhite;
        int startRow;
        int move;
        if (isWhite == true){
            startRow = 1;
            move = 1;
        }
        else {
            startRow = 6;
            move = -1;
        }
        if (start[1] == end[1] && chessboard.board[start[0] + move][start[1]] == null){
            if (start[0] + move == end[0]){
                System.out.println("A");
                return (true);
            }
            if (start[0] == startRow && start[0] + 2 * move == end[0] && chessboard.board[end[0]][end[1]] == null){
                System.out.println("B");
                return (true);
            }
        }
        if ((start[1] + 1 == end[1] || start[1] - 1 == end[1]) && start[0] + move == end[0]){
            if (chessboard.board[end[0]][end[1]] != null){
                System.out.println("C");
                if (chessboard.board[end[0]][end[1]] != null){
                    if (chessboard.board[end[0]][end[1]].isWhite == isWhite){
                        return (false);
                    }
                }
                return (true);
            }
            if (chessboard.board[start[0]][end[1]] instanceof Pawn){
                if (chessboard.board[start[0]][end[1]].doubleMoveTurn == chessboard.moveNum - 1){
                    System.out.println("D");
                    System.out.println(chessboard.board[start[0]][end[1]].doubleMoveTurn);
                    System.out.println(chessboard.moveNum);
                    if (chessboard.board[start[0]][end[1]].isWhite == isWhite){
                        return (false);
                    }
                }
                return (true);
            }
        }
        return (false);
    }
}