abstract class AbstractPiece {
    boolean isWhite;
    abstract boolean canMoveToTarget(int[] start, int[] end, Board chessboard);

    @Override public abstract String toString();
}
