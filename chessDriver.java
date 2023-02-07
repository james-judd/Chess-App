import java.util.HashMap;
import java.util.Arrays;

class chessDriver{
    public static void main(String[] args){
        Board chessboard = new Board();
        HashMap<String, Integer> repetitions = new HashMap<>(){{
            put(chessboard.boardString, 1);
        }};
        boolean threefold = false;
        boolean moveRule = false;
        chessboard.printBoard();
        while (!(threefold || moveRule)){
            int[] start = chessboard.choosePiece();
            int[] end = Input.takeInput(false);
            if (chessboard.isValidCastle(start, end) || 
            (!chessboard.isTurnColour(end) && chessboard.canMoveToTarget(start, end) && Arrays.equals(chessboard.lineOfSight(start, end), new int[2]))){
                chessboard.movePiece(start, end);
                if (repetitions.containsKey(chessboard.boardString)){
                    repetitions.put(chessboard.boardString, repetitions.get(chessboard.boardString) + 1);
                }
                else{
                    repetitions.put(chessboard.boardString, 1);
                }
                chessboard.checkmate();
                chessboard.stalemate();
                if (repetitions.get(chessboard.boardString) == 3){
                    threefold = true;
                }
                if (chessboard.moveRule == 100){
                    moveRule = true;
                }
            }
            else{
                System.out.println("Piece cannot move there, try again");
                continue;
            }
            chessboard.printBoard();
        }
        chessboard.gameOver(false, false, threefold, moveRule);
    }
}