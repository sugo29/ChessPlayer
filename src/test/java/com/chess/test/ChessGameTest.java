package com.chess.test;

import com.chess.controller.ChessGame;
import com.chess.model.*;
import com.chess.model.pieces.*;

/**
 * Simple test class to verify chess game functionality
 */
public class ChessGameTest {
    
    public static void main(String[] args) {
        System.out.println("Testing Chess Game Implementation...");
        
        testBasicGameFunctionality();
        testPieceMovement();
        testBoardInitialization();
        
        System.out.println("All tests completed!");
    }
    
    public static void testBasicGameFunctionality() {
        System.out.println("\n=== Testing Basic Game Functionality ===");
        
        ChessGame game = new ChessGame();
        
        // Test initial state
        String[][] board = game.getBoardState();
        System.out.println("Initial board created: " + (board != null ? "PASS" : "FAIL"));
        
        // Test game info
        var gameInfo = game.getGameInfo();
        System.out.println("Current player: " + gameInfo.get("currentPlayer"));
        System.out.println("Game state: " + gameInfo.get("gameState"));
        
        // Test a basic move (pawn forward)
        boolean moveResult = game.makeMove("e2", "e4");
        System.out.println("Pawn e2-e4 move: " + (moveResult ? "PASS" : "FAIL"));
        
        if (moveResult) {
            gameInfo = game.getGameInfo();
            System.out.println("Current player after move: " + gameInfo.get("currentPlayer"));
        }
    }
    
    public static void testPieceMovement() {
        System.out.println("\n=== Testing Piece Movement ===");
        
        ChessBoard board = new ChessBoard();
        
        // Test pawn movement
        ChessPiece pawn = new Pawn(PieceColor.WHITE, new Position(6, 4));
        Position oneSquareForward = new Position(5, 4);
        Position twoSquaresForward = new Position(4, 4);
        
        System.out.println("Pawn one square forward: " + 
            (pawn.isValidMove(oneSquareForward, board) ? "PASS" : "FAIL"));
        System.out.println("Pawn two squares forward (initial): " + 
            (pawn.isValidMove(twoSquaresForward, board) ? "PASS" : "FAIL"));
        
        // Test knight movement
        ChessPiece knight = new Knight(PieceColor.WHITE, new Position(7, 1));
        Position knightMove = new Position(5, 2);
        
        System.out.println("Knight L-shape move: " + 
            (knight.isValidMove(knightMove, board) ? "PASS" : "FAIL"));
        
        // Test rook movement
        ChessPiece rook = new Rook(PieceColor.WHITE, new Position(7, 0));
        Position rookMove = new Position(5, 0);
        
        // This should fail because pawns are in the way
        System.out.println("Rook vertical move (blocked): " + 
            (!rook.isValidMove(rookMove, board) ? "PASS" : "FAIL"));
    }
    
    public static void testBoardInitialization() {
        System.out.println("\n=== Testing Board Initialization ===");
        
        ChessBoard board = new ChessBoard();
        
        // Check if pieces are in correct starting positions
        ChessPiece whiteKing = board.getPieceAt(new Position(7, 4));
        System.out.println("White king at e1: " + 
            (whiteKing != null && whiteKing.getType() == PieceType.KING && 
             whiteKing.getColor() == PieceColor.WHITE ? "PASS" : "FAIL"));
        
        ChessPiece blackQueen = board.getPieceAt(new Position(0, 3));
        System.out.println("Black queen at d8: " + 
            (blackQueen != null && blackQueen.getType() == PieceType.QUEEN && 
             blackQueen.getColor() == PieceColor.BLACK ? "PASS" : "FAIL"));
        
        ChessPiece whitePawn = board.getPieceAt(new Position(6, 0));
        System.out.println("White pawn at a2: " + 
            (whitePawn != null && whitePawn.getType() == PieceType.PAWN && 
             whitePawn.getColor() == PieceColor.WHITE ? "PASS" : "FAIL"));
        
        // Check empty squares
        ChessPiece emptySquare = board.getPieceAt(new Position(4, 4));
        System.out.println("Empty square at e4: " + 
            (emptySquare == null ? "PASS" : "FAIL"));
    }
}
