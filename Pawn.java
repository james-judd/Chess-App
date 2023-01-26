class Pawn extends AbstractPiece {

    Pawn(boolean white){
        isWhite = white;
    }

    @Override
    public String toString(){
        return ("Pawn   ");
    }

    // boolean canMoveToTarget(Square target){
    //     if (target.row != current.row || target.column != current.column){
    //         if (){
    //             //Implement line-of-sight
    //             return (true);
    //         }
    //     }
    //     return (false);
    // }
}
