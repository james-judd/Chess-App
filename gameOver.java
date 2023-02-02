public class gameOver {

    gameOver (boolean whiteTurn, boolean checkmate, boolean stalemate, boolean repetition, boolean moveRule){
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
