package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UI {

    // ANSI color codes to format console text for better readability and user
    // experience
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // ANSI color codes for background colors, used to highlight selected or
    // possible move positions
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    // Clears the console screen to create a seamless user interface, especially
    // after each move
    // This provides a fresh display every time the board is updated
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Reads a chess position from user input in the form of 'column + row' (e.g.,
    // 'e2')
    // Validates and converts the input into a ChessPosition object, ensuring
    // correctness
    // Throws InputMismatchException if the input format is incorrect
    public static ChessPosition readChessPosition(Scanner scanner) {
        try {
            String coordinates = scanner.nextLine();
            char column = coordinates.charAt(0); // Extract the column character ('a' to 'h')
            int row = Integer.parseInt(coordinates.substring(1)); // Extract the row number ('1' to '8')
            return new ChessPosition(column, row); // Create and return a ChessPosition object
        } catch (RuntimeException e) {
            throw new InputMismatchException("Error getting input. Valid format: <column><row> (e.g., e2)."); // Detailed
                                                                                                              // error
                                                                                                              // message
                                                                                                              // for
                                                                                                              // user
                                                                                                              // guidance
        }
    }

    // Prints the current state of the chess match, including the board, captured
    // pieces, and status
    // Provides feedback to the player such as whose turn it is and whether they are
    // in check
    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
        printBoard(chessMatch.getPieces()); // Print the current board state
        System.out.println();
        printCapturedPieces(captured); // Display the list of captured pieces to provide context on game progress
        System.out.println();
        System.out.println("Turn: " + chessMatch.getTurn()); // Display the current turn number for clarity

        if (!chessMatch.getCheckMate()) {
            // If the game is ongoing, inform which player is expected to move and any check
            // condition
            System.out.println("Waiting for player: " + chessMatch.getCurrentPlayer());
            if (chessMatch.getCheck()) {
                System.out.println("CHECK!!"); // Indicate if the current player is in check, prompting action
            }
        } else {
            // If the game has ended, announce the winner and indicate checkmate
            System.out.println("CHECKMATE!");
            System.out.println("Winner: " + chessMatch.getCurrentPlayer());
        }
    }

    // Prints the entire chess board without highlighting, used to show the board
    // state normally
    // Iterates over the 2D array of ChessPiece objects to display each piece or an
    // empty space
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " "); // Print row numbers in descending order (8 to 1) for traditional chess
                                             // notation
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false); // Print each piece, passing 'false' to indicate no highlighting
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h"); // Print column labels ('a' to 'h') to match chess notation
    }

    // Prints the chess board with potential move positions highlighted
    // This version of the board print function visually assists players by marking
    // possible moves
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " "); // Print row numbers in descending order
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]); // Highlight positions based on 'possibleMoves' array
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h"); // Print column labels ('a' to 'h')
    }

    // Prints a single piece on the board, optionally highlighting its background if
    // it is a possible move
    // This helps distinguish between different types of pieces and valid moves,
    // improving gameplay experience
    public static void printPiece(ChessPiece piece, boolean background) {
        if (background) {
            System.out.print(ANSI_BLUE_BACKGROUND); // Highlight possible move positions with a blue background
        }
        if (piece == null) {
            System.out.print("-" + ANSI_RESET); // If no piece is present, print a dash ('-') to represent an empty
                                                // square
        } else {
            if (piece.getColor() == Color.WHITE) {
                // White pieces are printed in white for easy identification
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            } else {
                // Black pieces are printed in yellow for contrast on most console backgrounds
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" "); // Print space after each piece for proper board formatting
    }

    // Prints the captured pieces for each color, grouped by white and black
    // This provides a summary of pieces that have been removed from the board,
    // giving players insight into each other's status
    public static void printCapturedPieces(List<ChessPiece> captured) {
        // Separate captured pieces by color for better clarity
        List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
                .collect(Collectors.toList());
        List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK)
                .collect(Collectors.toList());

        // Print the list of captured white pieces, using white color formatting
        System.out.println("Captured pieces:");
        System.out.print("White: ");
        System.out.print(ANSI_WHITE);
        System.out.println(Arrays.toString(white.toArray()));
        System.out.print(ANSI_RESET);

        // Print the list of captured black pieces, using yellow color formatting for
        // visibility
        System.out.print("Black: ");
        System.out.print(ANSI_YELLOW);
        System.out.println(Arrays.toString(black.toArray()));
        System.out.print(ANSI_RESET);
    }
}
