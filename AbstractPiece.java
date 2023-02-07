abstract class AbstractPiece {
    boolean isWhite;
    boolean hasMoved;
    boolean[][] adjacentAttacked;
    int doubleMoveTurn;
    int[] pinner;

    void setHasMoved(){
        hasMoved = true;
    }
    boolean getHasMoved(){
        return (hasMoved);
    }
    void setAdjacentAttacked(boolean[][] array){
        adjacentAttacked = array;
    }
    boolean[][] getAdjacentAttacked(){
        return (adjacentAttacked);
    }
    void setAdjacentSquare(int row, int col, boolean attacked){
        adjacentAttacked[row][col] = attacked;
    }
    boolean getAdjacentSquare(int row, int col){
        return (adjacentAttacked[row][col]);
    }
    void setDoubleMoveTurn(int turn){
        doubleMoveTurn = turn;
    }
    int getDoubleMoveTurn(){
        return (doubleMoveTurn);
    }
    void setPinner(int[] square){
        pinner = square;
    }
    int[] getPinner(){
        return (pinner);
    }

    abstract boolean inRange(int[] start, int[] end);
    @Override public abstract String toString();
}