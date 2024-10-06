package BoardGame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;

    // Constructor that creates a board with a given number of rows and columns
    // Ensures that the board has at least one row and one column
    public Board(int rows, int columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardException("Error creating board: Must have at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns]; // Initializes the board as an empty 2D array of pieces
    }

    // Returns the piece located at a specific row and column
    // Throws an exception if the position is out of the board's bounds
    public Piece piece(int row, int column) {
        if (!positionExists(row, column)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[row][column];
    }

    // Returns the piece located at a given Position object
    // First checks if the provided position is valid within the board's boundaries
    public Piece piece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    // Places a given piece at the specified position on the board
    // Throws an exception if there is already a piece at the target position
    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("Position " + position + " is already occupied");
        }
        pieces[position.getRow()][position.getColumn()] = piece; // Place the piece on the board
        piece.position = position; // Update the piece's internal position to match its new location
    }

    // Removes a piece from the specified position on the board
    // Returns the removed piece, or null if there was no piece at that position
    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board"); // Ensure the position is valid
        }
        if (piece(position) == null) {
            return null; // No piece to remove at this position
        }

        Piece removedPiece = piece(position);
        removedPiece.position = null; // Clear the removed piece's position, since it's no longer on the board
        pieces[position.getRow()][position.getColumn()] = null; // Set the board cell to null to indicate it's empty

        return removedPiece; // Return the removed piece, allowing further processing if needed
    }

    // Checks if the position specified by row and column is within the board limits
    // Returns true if the row and column are valid, otherwise false
    public boolean positionExists(int row, int column) {
        return row >= 0 && row < rows && column >= 0 && column < columns;
    }

    // Checks if a given Position object is within the boundaries of the board
    // Uses the row and column values from the Position to determine validity
    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    // Determines whether there is a piece at the given position
    // Throws an exception if the position itself is not valid
    // Returns true if a piece is found at the specified position, false otherwise
    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Position not on the board");
        }
        return piece(position) != null; // Check if the board cell contains a piece
    }
}
