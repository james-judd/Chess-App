class Board {
    AbstractPiece[][] board = new AbstractPiece[8][8];

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
    }

    public void printBoard(){
        System.out.println();
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if (board[i][j] != null){
                    System.out.print(board[i][j].toString());
                }
                else {
                    System.out.print("Null   ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    public static boolean isStalemate(AbstractPiece[][] board, boolean isWhite){
        //Stalemate
        return (false);
    }

    public static boolean hasWon(AbstractPiece[][] board, boolean isWhite){
        //Has Won
        return (false);
    }
}
