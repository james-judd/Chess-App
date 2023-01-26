class Bishop extends AbstractPiece {

    Bishop(boolean white){
        isWhite = white;
    }

    @Override
    public String toString(){
        return ("Bishop ");
    }

    // boolean canMoveToTarget(Square current, Square target){
    //     if (target.row != current.row || target.column != current.column){
    //         if (Math.abs(target.row - current.row) == Math.abs(target.column - current.column){
    //             //Implement line-of-sight
    //             return (true);
    //         }
    //     }
    //     return (false);
    // }
}
