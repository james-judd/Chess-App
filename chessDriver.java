import java.util.HashMap;

class chessDriver{
    public static void main(String[] args){
        Board chessboard = new Board();
        HashMap<String, Integer> repetitions = new HashMap<>(){{
            put(chessboard.boardString, 1);
        }};
        boolean whiteTurn = true;
        boolean checkmate = false;
        boolean stalemate = false;
        boolean repetition = false;
        boolean moveRule = false;
        while (!(checkmate || stalemate || repetition || moveRule)){
            chessboard.printBoard();
            // System.out.println(chessboard.boardString);
            int[] start = Input.takeStart(chessboard);
            int[] end = Input.takeInput(false);   
            if (chessboard.canMoveToTarget(start, end, chessboard)){ 
                if (chessboard.hasLineOfSight(start, end)){ 
                    chessboard.movePiece(start, end);
                    if (repetitions.containsKey(chessboard.boardString)){
                        repetitions.put(chessboard.boardString, repetitions.get(chessboard.boardString) + 1);
                    }
                    else{
                        repetitions.put(chessboard.boardString, 1);
                    }
                    whiteTurn = !whiteTurn;
                    checkmate = chessboard.checkmate(whiteTurn);
                    stalemate = chessboard.stalemate(whiteTurn);
                    if (repetitions.get(chessboard.boardString) == 3){
                        repetition = true;
                    }
                    if (chessboard.moveRule == 50){
                        moveRule = true;
                    }
                }
            }
        }
        chessboard.printBoard();
        new gameOver(whiteTurn, checkmate, stalemate, repetition, moveRule);
    }
}