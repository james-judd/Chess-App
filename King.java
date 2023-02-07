class King extends AbstractPiece {
    
    King(boolean whiteBool){
        isWhite = whiteBool;
        hasMoved = false;
        adjacentAttacked = new boolean[3][5];
    }

    void setHasMoved(){
        hasMoved = true;
    }
    boolean getHasMoved(){
        return (hasMoved);
    }

    @Override public String toString(){
        if (isWhite){
            return("KING");
        }
        return("king");
    }

    boolean inRange(int[] start, int[] end){
        int relativeRow = end[0] - start[0];
        int relativeCol = end[1] - start[1];
        if (start[0] != end[0] || start[1] != end[1]){
            if (Math.abs(relativeRow) <= 1 && Math.abs(relativeCol) <= 1){
                if (!adjacentAttacked[relativeRow + 1][relativeCol + 2]){
                    return (true);
                }
            }
        }
        return (false);
    }
}