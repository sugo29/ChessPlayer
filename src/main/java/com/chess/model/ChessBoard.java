package com.chess.model;

import com.chess.model.pieces.*;
import java.util.*;

/**
 * Chess board implementation using 2D array data structure
 */
public class ChessBoard {
    private static final int BOARD_SIZE = 8;
    private ChessPiece[][] board;
    private List<Move> moveHistory;
    private PieceColor currentPlayer;
    
    public ChessBoard() {
        board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        moveHistory = new ArrayList<>();
        currentPlayer = PieceColor.WHITE;
        initializeBoard();
    }
    
    /**
     * Initialize the chess board with pieces in starting positions
     */
    private void initializeBoard() {
        // Place white pieces
        board[7][0] = new Rook(PieceColor.WHITE, new Position(7, 0));
        board[7][1] = new Knight(PieceColor.WHITE, new Position(7, 1));
        board[7][2] = new Bishop(PieceColor.WHITE, new Position(7, 2));
        board[7][3] = new Queen(PieceColor.WHITE, new Position(7, 3));
        board[7][4] = new King(PieceColor.WHITE, new Position(7, 4));
        board[7][5] = new Bishop(PieceColor.WHITE, new Position(7, 5));
        board[7][6] = new Knight(PieceColor.WHITE, new Position(7, 6));
        board[7][7] = new Rook(PieceColor.WHITE, new Position(7, 7));
        
        for (int col = 0; col < BOARD_SIZE; col++) {
            board[6][col] = new Pawn(PieceColor.WHITE, new Position(6, col));
        }
        
        // Place black pieces
        board[0][0] = new Rook(PieceColor.BLACK, new Position(0, 0));
        board[0][1] = new Knight(PieceColor.BLACK, new Position(0, 1));
        board[0][2] = new Bishop(PieceColor.BLACK, new Position(0, 2));
        board[0][3] = new Queen(PieceColor.BLACK, new Position(0, 3));
        board[0][4] = new King(PieceColor.BLACK, new Position(0, 4));
        board[0][5] = new Bishop(PieceColor.BLACK, new Position(0, 5));
        board[0][6] = new Knight(PieceColor.BLACK, new Position(0, 6));
        board[0][7] = new Rook(PieceColor.BLACK, new Position(0, 7));
        
        for (int col = 0; col < BOARD_SIZE; col++) {
            board[1][col] = new Pawn(PieceColor.BLACK, new Position(1, col));
        }
    }
    
    /**
     * Get piece at specified position
     */
    public ChessPiece getPieceAt(Position position) {
        if (!position.isValid()) {
            return null;
        }
        return board[position.getRow()][position.getCol()];
    }
    
    /**
     * Set piece at specified position
     */
    public void setPieceAt(Position position, ChessPiece piece) {
        if (position.isValid()) {
            board[position.getRow()][position.getCol()] = piece;
        }
    }
    
    /**
     * Remove piece from specified position
     */
    public ChessPiece removePieceAt(Position position) {
        if (!position.isValid()) {
            return null;
        }
        ChessPiece piece = board[position.getRow()][position.getCol()];
        board[position.getRow()][position.getCol()] = null;
        return piece;
    }
    
    /**
     * Check if a path between two positions is clear
     */
    public boolean isPathClear(Position from, Position to) {
        int rowDir = Integer.compare(to.getRow() - from.getRow(), 0);
        int colDir = Integer.compare(to.getCol() - from.getCol(), 0);
        
        int currentRow = from.getRow() + rowDir;
        int currentCol = from.getCol() + colDir;
        
        while (currentRow != to.getRow() || currentCol != to.getCol()) {
            if (board[currentRow][currentCol] != null) {
                return false;
            }
            currentRow += rowDir;
            currentCol += colDir;
        }
        
        return true;
    }
    
    /**
     * Make a move on the board
     */
    public boolean makeMove(Move move) {
        ChessPiece piece = getPieceAt(move.getFrom());
        
        if (piece == null || piece.getColor() != currentPlayer) {
            return false;
        }
        
        if (!piece.isValidMove(move.getTo(), this)) {
            return false;
        }
        
        // Perform the move
        ChessPiece capturedPiece = removePieceAt(move.getTo());
        removePieceAt(move.getFrom());
        setPieceAt(move.getTo(), piece);
        piece.setPosition(move.getTo());
        
        move.setCapturedPiece(capturedPiece);
        moveHistory.add(move);
        
        // Switch players
        currentPlayer = currentPlayer.opposite();
        
        return true;
    }
    
    /**
     * Get current player
     */
    public PieceColor getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * Get move history
     */
    public List<Move> getMoveHistory() {
        return new ArrayList<>(moveHistory);
    }
    
    /**
     * Get board state as 2D array for JSON serialization
     */
    public String[][] getBoardState() {
        String[][] state = new String[BOARD_SIZE][BOARD_SIZE];
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                ChessPiece piece = board[row][col];
                if (piece != null) {
                    state[row][col] = piece.getColor().toString().charAt(0) + 
                                     piece.getType().getSymbol();
                } else {
                    state[row][col] = null;
                }
            }
        }
        return state;
    }
    
    /**
     * Find the king of specified color
     */
    public Position findKing(PieceColor color) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                ChessPiece piece = board[row][col];
                if (piece != null && piece.getType() == PieceType.KING && 
                    piece.getColor() == color) {
                    return new Position(row, col);
                }
            }
        }
        return null;
    }
}
