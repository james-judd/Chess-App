class Rook extends AbstractPiece {

    Rook(boolean white){
        isWhite = white;
    }

    @Override
    public String toString(){
        return ("Rook   ");
    }


    // boolean canMoveToTarget(Square target){
    //     if (target.row != current.row || target.column != current.column){
    //         if (target.row == current.row || target.column == current.column){
    //             //Implement line-of-sight
    //             return (true);
    //         }
    //     }
    //     return (false);
    // }
}
