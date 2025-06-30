package com.chess.controller;

import com.chess.model.*;
import java.util.*;

/**
 * Chess game controller implementing game logic and state management
 */
public class ChessGame {
    private ChessBoard board;
    private GameState gameState;
    private Map<String, Object> gameInfo;
    
    public enum GameState {
        ACTIVE, WHITE_WIN, BLACK_WIN, DRAW, STALEMATE
    }
    
    public ChessGame() {
        this.board = new ChessBoard();
        this.gameState = GameState.ACTIVE;
        this.gameInfo = new HashMap<>();
        initializeGameInfo();
    }
    
    private void initializeGameInfo() {
        gameInfo.put("currentPlayer", board.getCurrentPlayer().toString());
        gameInfo.put("gameState", gameState.toString());
        gameInfo.put("moveCount", 0);
        gameInfo.put("lastMove", null);
    }
    
    /**
     * Attempt to make a move
     */
    public boolean makeMove(String fromPos, String toPos) {
        try {
            Position from = new Position(fromPos);
            Position to = new Position(toPos);
            Move move = new Move(from, to);
            
            if (board.makeMove(move)) {
                updateGameInfo(move);
                checkGameState();
                return true;
            }
        } catch (IllegalArgumentException e) {
            // Invalid position format
        }
        return false;
    }
    
    /**
     * Update game information after a move
     */
    private void updateGameInfo(Move move) {
        gameInfo.put("currentPlayer", board.getCurrentPlayer().toString());
        gameInfo.put("lastMove", move.toString());
        gameInfo.put("moveCount", (Integer) gameInfo.get("moveCount") + 1);
    }
    
    /**
     * Check current game state (win/draw conditions)
     */
    private void checkGameState() {
        // Simplified game state checking
        // In a full implementation, would check for checkmate, stalemate, etc.
        gameInfo.put("gameState", gameState.toString());
    }
    
    /**
     * Get current board state
     */
    public String[][] getBoardState() {
        return board.getBoardState();
    }
    
    /**
     * Get game information
     */
    public Map<String, Object> getGameInfo() {
        return new HashMap<>(gameInfo);
    }
    
    /**
     * Get valid moves for a piece at given position
     */
    public List<String> getValidMoves(String position) {
        List<String> validMoves = new ArrayList<>();
        try {
            Position pos = new Position(position);
            ChessPiece piece = board.getPieceAt(pos);
            
            if (piece != null && piece.getColor() == board.getCurrentPlayer()) {
                // Check all possible moves on the board
                for (int row = 0; row < 8; row++) {
                    for (int col = 0; col < 8; col++) {
                        Position target = new Position(row, col);
                        if (piece.isValidMove(target, board)) {
                            validMoves.add(target.toAlgebraic());
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            // Invalid position
        }
        return validMoves;
    }
    
    /**
     * Reset the game
     */
    public void resetGame() {
        this.board = new ChessBoard();
        this.gameState = GameState.ACTIVE;
        initializeGameInfo();
    }
    
    /**
     * Get move history
     */
    public List<Move> getMoveHistory() {
        return board.getMoveHistory();
    }
}
