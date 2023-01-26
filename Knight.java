class Knight extends AbstractPiece {

    Knight(boolean white){
        isWhite = white;
    }

    @Override
    public String toString(){
        return ("Knight ");
    }

    // boolean canMoveToTarget(Square target){
    //     if (target.row != current.row || target.column != current.column){
    //         if (Math.abs(target.row - current.row) + Math.abs(target.column - current.column) == 3 ){
    //             //Implement line-of-sight
    //             return (true);
    //         }
    //     }
    //     return (false);
    // }
}
