package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Scanner for reading user input

        ChessMatch chessMatch = new ChessMatch(); // Creating a new chess match instance
        List<ChessPiece> captured = new ArrayList<>(); // List to keep track of captured pieces

        // Main game loop, runs until a checkmate occurs
        while (!chessMatch.getCheckMate()) {
            try {
                UI.clearScreen(); // Clears the console screen for a fresh display
                UI.printMatch(chessMatch, captured); // Prints the current state of the match
                System.out.println();

                // Prompt user for the source position of a piece to move
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                // Get the possible moves for the selected piece
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen(); // Clear the screen before showing possible moves
                UI.printBoard(chessMatch.getPieces(), possibleMoves); // Display the board highlighting possible moves

                System.out.println();

                // Prompt user for the target position to move the piece
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                // Perform the move and check if any piece was captured
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if (capturedPiece != null) {
                    captured.add(capturedPiece); // Add the captured piece to the list
                }

                // Check if a pawn promotion is possible
                if (chessMatch.getPromoted() != null) {
                    System.out.print("Promote to [B,K,R,Q]: ");
                    String type = sc.next().toUpperCase();

                    // Validate the input for promotion type
                    while (!type.equals("B") && !type.equals("N") && !type.equals("R") && !type.equals("Q")) {
                        System.out.print("Invalid piece! Promote to [B,K,R,Q]: ");
                        type = sc.next().toUpperCase();
                    }

                    // Replace the promoted piece with the selected type
                    chessMatch.replacePromotedPiece(type);
                }
            } catch (ChessException e) {
                // Handle chess-specific exceptions, such as invalid moves
                System.out.println(e.getMessage());
                sc.nextLine(); // Clear the input buffer
            } catch (InputMismatchException e) {
                // Handle incorrect input types
                System.out.println(e.getMessage());
                sc.nextLine(); // Clear the input buffer
            }
        }

        // Final display when the game ends in checkmate
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
