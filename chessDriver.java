import java.util.HashMap;
import java.util.Arrays;

class chessDriver{
    public static void main(String[] args){
        Board chessboard = new Board();
        HashMap<String, Integer> repetitions = new HashMap<>(){{
            put(chessboard.boardString, 1);
        }};
        boolean checkmate = false;
        boolean stalemate = false;
        boolean repetition = false;
        boolean moveRule = false;
        chessboard.printBoard();
        while (!(checkmate || stalemate || repetition || moveRule)){
            int[] start = chessboard.choosePiece();
            int[] end = Input.takeInput(false);
            if (!chessboard.isTurnColour(end) && chessboard.canMoveToTarget(start, end)){
                if (Arrays.equals(chessboard.lineOfSight(start, end), new int[2])){ 
                    chessboard.movePiece(start, end);
                    if (repetitions.containsKey(chessboard.boardString)){
                        repetitions.put(chessboard.boardString, repetitions.get(chessboard.boardString) + 1);
                    }
                    else{
                        repetitions.put(chessboard.boardString, 1);
                    }
                    chessboard.whiteTurn = !chessboard.whiteTurn;
                    checkmate = chessboard.checkmate();
                    stalemate = chessboard.stalemate();
                    if (repetitions.get(chessboard.boardString) == 3){
                        repetition = true;
                    }
                    if (chessboard.moveRule == 100){
                        moveRule = true;
                    }
                }
                else{
                    System.out.println("Piece is blocked, try again");
                    continue;
                }
            }
            else{
                System.out.println("Piece cannot move there, try again");
                continue;
            }
            chessboard.printBoard();
        }
        chessboard.gameOver(checkmate, stalemate, repetition, moveRule);
    }
}