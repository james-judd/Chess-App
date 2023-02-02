class Board {
    public AbstractPiece[][] board = new AbstractPiece[8][8];
    public String boardString;
    public int moveRule = 0;

    public Board(){
        board[0][0] = board[0][7] = new Rook(true);
        board[0][1] = board[0][6] = new Knight(true);
        board[0][2] = board[0][5] = new Bishop(true);
        board[0][3] = new Queen(true);
        board[0][4] = new King(true);
        for (int i = 0; i < 8; i++){
            board[1][i] = new Pawn(true);
        }
        board[7][0] = board[7][7] = new Rook(false);
        board[7][1] = board[7][6] = new Knight(false);
        board[7][2] = board[7][5] = new Bishop(false);
        board[7][3] = new Queen(false);
        board[7][4] = new King(false);
        for (int i = 0; i < 8; i++){
            board[6][i] = new Pawn(false);
        }
        boardString = toString();
    }

    @Override public String toString(){
        String str = "";
        for (int row = 7; row >= 0; row--){
            for (int col = 0; col < 8; col++){
                if (board[row][col] == null){
                    str += ".";
                }
                else{
                    str += board[row][col].toString();
                }
            }
        }
        return (str);
    }

    public void printBoard(){
        System.out.println();
        for (int row = 7; row >= 0; row--){
            System.out.print((row + 1) + "      ");
            for (int col = 0; col < 8; col++){
                if (board[row][col] == null){
                    System.out.print("|____| ");
                }
                else {
                    System.out.print(board[row][col].toString());
                    for (int i = 1; i <= (7 - board[row][col].toString().length()); i++){
                        System.out.print(" ");
                    };
                }
            }
            System.out.println();
            System.out.println();
        }
        String[] labels = {"       ", "A      ","B      ","C      ","D      ","E      ","F      ","G      ","H      "};
        for (String str : labels)
        System.out.print(str);
        System.out.println();
        System.out.println();
    }

    public boolean canMoveToTarget(int[] start, int[] end, Board chessboard){      
        return (board[start[0]][start[1]].canMoveToTarget(start, end, chessboard));
    }

    public boolean hasLineOfSight(int[] start, int[] end){
        if (start[0] == end[0]){
            int minCol = Math.min(start[1], end[1]) + 1;
            int maxCol = Math.max(start[1], end[1]);
            while (minCol < maxCol){
                if (board[start[0]][minCol] != null){
                    return (false);
                }
                minCol++;
            }
        }
        else if (start[1] == end[1]){
            int minRow = Math.min(start[0], end[0]) + 1;
            int maxRow = Math.max(start[0], end[0]);
            while (minRow < maxRow){
                if (board[minRow][start[1]] != null){
                    return (false);
                }
                minRow++;
            }
        }
        else{
            int rowIterate = (end[0] - start[0]) / Math.abs(end[0] - start[0]);
            int colIterate = (end[1] - start[1]) / Math.abs(end[1] - start[1]);
            start[0] += rowIterate;
            start[1] += colIterate;
            while (start[0] != end[0]){
                if (board[start[0]][start[1]] != null){
                    return (false);
                }
                start[0] += rowIterate;
                start[1] += colIterate;
            }
        }
        return (true);
    }

    public void movePiece(int[] start, int[] end){
        moveRule++;
        if ((board[start[0]][start[1]] instanceof Pawn) || board[end[0]][end[1]] != null){
            moveRule = 0;
        }
        board[end[0]][end[1]] = board[start[0]][start[1]];
        board[start[0]][start[1]] = null;
        boardString = toString();
    }

    public boolean checkmate(boolean whiteTurn){
        //Has Won
        return (false);
    }

    public boolean stalemate(boolean whiteTurn){
        //Stalemate
        return (false);
    }
}