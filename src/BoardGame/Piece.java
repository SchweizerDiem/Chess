package BoardGame;

public abstract class Piece {
    protected Position position; // Holds the current position of the piece on the board
    private Board board; // Reference to the board where the piece is located

    // Constructor that initializes the piece with a reference to the board
    // The position starts as null because the piece might not be placed on the
    // board initially
    public Piece(Board board) {
        this.board = board;
        // Position starts as null until the piece is placed on the board
    }

    // Protected getter to access the board reference
    // This ensures that only subclasses and classes within the same package can
    // access the board
    protected Board getBoard() {
        return board;
    }

    // Abstract method that must be implemented by all subclasses
    // Returns a boolean matrix representing the possible moves for the piece
    public abstract boolean[][] possibleMoves();

    // Returns whether a specific position is a valid move for the piece
    // Uses the result from the possibleMoves() matrix
    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    // Checks if the piece has at least one possible move
    // Iterates through the possibleMoves matrix, returning true if any move is
    // possible
    public boolean isThereAnyPossibleMove() {
        boolean[][] mat = possibleMoves();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                if (mat[i][j]) {
                    return true; // Returns true as soon as a valid move is found
                }
            }
        }
        return false; // Returns false if no valid move is found
    }
}
