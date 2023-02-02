abstract class AbstractPiece {
    boolean isWhite;
    int doubleMoveTurn;
    abstract boolean canMoveToTarget(int[] start, int[] end, Board chessboard);

    @Override public abstract String toString();
}
