class Queen extends AbstractPiece {

    Queen(boolean white){
        isWhite = white;
    }

    @Override
    public String toString(){
        return ("Queen  ");
    }

    // boolean canMoveToTarget(Square target){
    //     if (target.row != current.row || target.column != current.column){
    //         if (target.row == current.row || target.column == current.column || Math.abs(target.row - current.row) == Math.abs(target.column - current.column){
    //             //Implement line-of-sight
    //             return (true);
    //         }
    //     }
    //     return (false);
    // }
}
