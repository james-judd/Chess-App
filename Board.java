import java.util.Arrays;

class Board {
    public AbstractPiece[][] board = new AbstractPiece[8][8];
    public String boardString;
    public boolean whiteTurn = true;
    public int moveNum = 1;
    public int moveRule = 0;
    public int[] whiteKing = {0, 4};
    public int[] blackKing = {7, 4};
    int[] checker = null;

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
        String[] labels = {"       ", "   A   ","   B   ","   C   ","   D   ","   E   ","   F   ","   G   ","   H   "};
        for (String str : labels)
        System.out.print(str);
        System.out.println();
        System.out.println();
    }

    public int[] getKing(){
        if (whiteTurn){
            return (whiteKing);
        }
        return (blackKing);
    }

    public int[] choosePiece(){
        int[] input = Input.takeInput(true);
        while (!isTurnColour(input)){
                System.out.println("Invalid piece, try again");
                input = Input.takeInput(true);
            }
        return (input);
    }

    public boolean isTurnColour(int[] square){
        if (board[square[0]][square[1]] != null){
            if (board[square[0]][square[1]].isWhite == whiteTurn){
                return (true);
            }
        }
        return (false);
    }

    public boolean canMoveToTarget(int[] start, int[] end){
        if (!(board[start[0]][start[1]] instanceof King) && checker != null){
            if (Arrays.equals(checker, new int[]{-1, -1})){
                return (false);
            }
            if (!isSquareBetweenKing(end, checker)){
                return (false);
            }
        }
        int[] pinner = board[start[0]][start[1]].getPinner();
        if (pinner != null){
            if (!isSquareBetweenKing(end, pinner)){
                return (false);
            }
        }
        if (board[start[0]][start[1]] instanceof Pawn){
            return (inPawnRange(start, end));
        }
        return (board[start[0]][start[1]].inRange(start, end));
    }

    public boolean isSquareBetweenKing(int[] square, int[] attacker){
        int[] king = getKing();
        if (!(board[attacker[0]][attacker[1]] instanceof Knight) && Math.abs(square[0] - king[0]) != Math.abs(square[1] - king[1]) && Math.abs(square[0] - king[0]) != 0 && Math.abs(square[1] - king[1]) != 0){
            return (false);
        }
        int rowIterate = 0;
        int colIterate = 0;
        if (king[0] != attacker[0]){
            rowIterate = (attacker[0] - king[0]) / Math.abs(attacker[0] - king[0]);
        }
        if (king[1] != attacker[1]){
            colIterate = (attacker[1] - king[1]) / Math.abs(attacker[1] - king[1]);
        }
        int row = attacker[0];
        int col = attacker[1];
        while (row != king[0] || col != king[1]){
            if (row == square[0] && col == square[1]){
                return (true);
            }
            if (board[attacker[0]][attacker[1]] instanceof Knight){
                break;
            }
            else{
                row -= rowIterate;
                col -= colIterate;
            }
        }
        return (false);
    }

    public boolean inPawnRange(int[] start, int[] end){
        int startRow = 1;
        int move = 1;
        if (!whiteTurn){
            startRow = 6;
            move = -1;
        }
        if (start[1] == end[1] && board[start[0] + move][start[1]] == null){
            if (start[0] + move == end[0]){
                return (true);
            }
            if (start[0] == startRow && start[0] + 2 * move == end[0] && board[end[0]][end[1]] == null){
                return (true);
            }
        }
        if ((start[1] + 1 == end[1] || start[1] - 1 == end[1]) && start[0] + move == end[0]){
            if (board[end[0]][end[1]] != null){
                if (board[end[0]][end[1]].isWhite == whiteTurn){
                    return (false);
                }
                return (true);
            }
            if (board[start[0]][end[1]] instanceof Pawn){
                if (board[start[0]][end[1]].getDoubleMoveTurn() == moveNum - 1){
                    if (board[start[0]][end[1]].isWhite != whiteTurn){
                        return (true);
                    }
                }
            }
        }
        return (false);
    }

    public int[] lineOfSight(int[] start, int[] end){    
        int[] pinned = new int[2];  
        if (!(board[start[0]][start[1]] instanceof Knight)){
            if (Math.abs(end[0] - start[0]) != Math.abs(end[1] - start[1]) && Math.abs(end[0] - start[0]) != 0 && Math.abs(end[1] - start[1]) != 0){
                return (null);
            }
            int rowIterate = 0;
            int colIterate = 0;
            if (start[0] != end[0]){
                rowIterate = (end[0] - start[0]) / Math.abs(end[0] - start[0]);
            }
            if (start[1] != end[1]){
                colIterate = (end[1] - start[1]) / Math.abs(end[1] - start[1]);
            }
            int row = start[0] + rowIterate;
            int col = start[1] + colIterate;
            while (row != end[0] || col != end[1]){
                if (board[row][col] != null){
                    if (board[row][col].isWhite != board[start[0]][start[1]].isWhite){
                        if (Arrays.equals(pinned, new int[2])){
                            pinned[0] = row;
                            pinned[1] = col;
                        }
                        else{
                            return (null);
                        }
                    }
                    else{
                        return (null);
                    }
                }
                row += rowIterate;
                col += colIterate;
            }
        }       
        return (pinned);
    }

    public void movePiece(int[] start, int[] end){  
        moveRule++;
        if (board[end[0]][end[1]] != null){
            moveRule = 0;
        }
        if (board[start[0]][start[1]] instanceof Pawn){
            moveRule = 0;
            if (end[0] == start[0] + 2 || end[0] == start[0] - 2){
                board[start[0]][start[1]].setDoubleMoveTurn(moveNum);
            }
            else if (start[1] != end[1] && board[start[0]][end[1]] instanceof Pawn && board[start[0]][end[1]].getDoubleMoveTurn() == moveNum - 1){
                board[start[0]][end[1]] = null;
            }
            else if (end[0] == 0 || end[0] == 7){
                promotePawn(start);
            }
        }
        else if (board[start[0]][start[1]] instanceof King){
            if (whiteTurn){
                whiteKing[0] = end[0];
                whiteKing[1] = end[1];
            }
            else{
                blackKing[0] = end[0];
                blackKing[1] = end[1];                
            }
        }
        board[end[0]][end[1]] = board[start[0]][start[1]];
        board[start[0]][start[1]] = null;
        boardString = toString();
        moveNum++;
    }

    public void promotePawn(int[] start){
        String input = null;
        System.out.println("Promotion options:  1) Queen  2) Rook  3) Bishop  4) Knight");
        System.out.print("Choose promotion: ");
        input = System.console().readLine();
        if (input.equals("1") || input.toLowerCase().equals("queen")){
            board[start[0]][start[1]] = new Queen(whiteTurn);
        }
        else if (input.equals("2") || input.toLowerCase().equals("rook")){
            board[start[0]][start[1]] = new Rook(whiteTurn);
        }
        else if (input.equals("3") || input.toLowerCase().equals("bishop")){
            board[start[0]][start[1]] = new Bishop(whiteTurn);
        }
        else{
            board[start[0]][start[1]] = new Knight(whiteTurn);
        }
    }

    public boolean checkmate(){
        checker = inCheck();
        if (checker != null){
            int[] king = getKing();
            for (int rowAdj = 0; rowAdj < 3; rowAdj++){
                for (int colAdj = 0; colAdj < 3; colAdj++){
                    if (!board[king[0]][king[1]].getAdjacentSquare(rowAdj, colAdj)){
                        System.out.println("Check!");
                        return (false);
                    }
                }
            }
            int[] doubleCheck = {-1, -1};
            if (Arrays.equals(checker, doubleCheck)){
                System.out.println("Checkmate!");
                return (true);
            }
            int rowIterate = 0;
            int colIterate = 0;
            if (king[0] != checker[0]){
                rowIterate = (checker[0] - king[0]) / Math.abs(checker[0] - king[0]);
            }
            if (king[1] != checker[1]){
                colIterate = (checker[1] - king[1]) / Math.abs(checker[1] - king[1]);
            }
            for (int row = 0; row < 8; row++){
                for (int col = 0; col < 8; col++){
                    int[] start = {row, col};  
                    if (isTurnColour(start) && (row != king[0] || col != king[1])){  
                        int blockRow = checker[0];
                        int blockCol = checker[1];
                        while (blockRow != king[0] || blockCol != king[1]){                                    
                            int[] end = {blockRow, blockCol};
                            if (canMoveToTarget(start, end) && Arrays.equals(lineOfSight(start, end), new int[2])){
                                System.out.println("Check!");
                                return (false);
                            }
                            if (board[checker[0]][checker[1]] instanceof Knight){
                                break;
                            }
                            else{
                                blockRow -= rowIterate;
                                blockCol -= colIterate;
                            }
                        }
                    }
                }
            }
            System.out.println("Checkmate!");
            return (true);
        }
        return (false);                
    }

    public int[] inCheck(){
        checker = null;
        int[] king = getKing();
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if (isTurnColour(new int[]{row, col})){
                    board[row][col].setPinner(null);
                }
            }
        }
        board[king[0]][king[1]].setAdjacentAttacked(new boolean[8][8]);
        for (int rowAdj = 0; rowAdj < 3; rowAdj++){
            for (int colAdj = 0; colAdj < 3; colAdj++){
                if (rowAdj == 1 && colAdj == 1){
                    continue;
                }
                int[] square = {king[0] + rowAdj - 1, king[1] + colAdj - 1};
                if (square[0] >= 0 && square[0] < 8 && square[1] >= 0 && square[1] < 8){
                    if (isTurnColour(square)){
                        board[king[0]][king[1]].setAdjacentSquare(rowAdj, colAdj, true);
                    }
                }
                else{
                    board[king[0]][king[1]].setAdjacentSquare(rowAdj, colAdj, true);
                }
            }
        }
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if (board[row][col] != null){
                    if (board[row][col].isWhite != whiteTurn){
                        for (int rowAdj = 0; rowAdj < 3; rowAdj++){
                            for (int colAdj = 0; colAdj < 3; colAdj++){
                                if (!board[king[0]][king[1]].getAdjacentSquare(rowAdj, colAdj) || (rowAdj == 1 && colAdj == 1)){
                                    int[] start = {row, col};
                                    int[] end = {king[0] + rowAdj - 1, king[1] + colAdj - 1};
                                    if (end[0] >= 0 && end[0] < 8 && end[1] >= 0 && end[1] < 8){
                                        if (!(board[row][col] instanceof Pawn)){
                                            if (board[row][col].inRange(start, end)){
                                                int[] pinned = lineOfSight(start, end);
                                                if (Arrays.equals(pinned, new int[2])){
                                                    board[king[0]][king[1]].setAdjacentSquare(rowAdj, colAdj, true);
                                                    if (rowAdj == 1 && colAdj == 1){
                                                        if (checker == null){
                                                            checker = start;
                                                        }
                                                        else{
                                                            checker[0] = -1;
                                                            checker[1] = -1;
                                                        }
                                                    }
                                                }
                                                else if (pinned != null && rowAdj == 1 && colAdj == 1){
                                                    board[pinned[0]][pinned[1]].setPinner(start);
                                                }
                                            }
                                        }
                                        else {
                                            int move = 1;
                                            if (whiteTurn){
                                                move = -1;
                                            }
                                            if ((col + 1 == end[1] || col - 1 == end[1]) && row + move == end[0]){
                                                board[king[0]][king[1]].setAdjacentSquare(rowAdj, colAdj, true);
                                                if (rowAdj == 1 && colAdj == 1){
                                                    if (checker == null){
                                                        checker = start;
                                                    }
                                                    else{
                                                        checker[0] = -1;
                                                        checker[1] = -1;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return (checker);
    }

    public boolean canMove(int[] start){
        if (board[start[0]][start[1]] instanceof Knight){
            if (board[start[0]][start[1]].getPinner() == null){
                int[] rows = {-2, -2, -1, -1, 1, 1, 2, 2};
                int[] cols = {1, -1, 2, -2, 2, -2, 1, -1};
                for (int i = 0; i < 7; i++){
                    int[] end = {start[0] + rows[i], start[1] + cols[i]};
                    if (end[0] >= 0 && end[0] < 8 && end[1] >= 0 && end[1] < 8){
                        if (!isTurnColour(end)){
                            return (true);
                        }
                    }
                }
            }
        }
        else{
            for (int rowAdj = -1; rowAdj < 2; rowAdj++){
                for (int colAdj = -1; colAdj < 2; colAdj++){
                    int[] end = {start[0] + rowAdj, start[1] + colAdj};
                    if (end[0] >= 0 && end[0] < 8 && end[1] >= 0 && end[1] < 8){
                        if (!isTurnColour(end) && canMoveToTarget(start, end)){
                            return (true);
                        }
                    }
                }
            }
        }
        return (false);
    }

    public boolean stalemate(){
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                int[] start = {row, col};
                if (isTurnColour(start)){
                    if (canMove(start)){
                        return (false);
                    }
                }
            }
        }
        System.out.println("Stalemate");
        return (true);
    }

    public void gameOver (boolean checkmate, boolean stalemate, boolean repetition, boolean moveRule){
        if (checkmate){
            String colour = "white";
            if (whiteTurn){
                colour = "black";
            }
            System.out.println("Congratulations, " + colour + " won by checkmate!");
        }
        else if (stalemate){
            System.out.println("Draw by stalemate");
        }
        else if (repetition){
            System.out.println("Draw by threefold repetition");
        }
        else{
            System.out.println("Draw by the 50 move rule");
        }
        System.exit(0);
    }
}