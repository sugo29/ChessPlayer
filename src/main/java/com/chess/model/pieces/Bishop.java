package com.chess.model.pieces;

import com.chess.model.*;

/**
 * Bishop piece implementation
 */
public class Bishop extends ChessPiece {
    
    public Bishop(PieceColor color, Position position) {
        super(color, PieceType.BISHOP, position);
    }
    
    @Override
    public boolean isValidMove(Position target, ChessBoard board) {
        if (target.equals(position)) {
            return false;
        }
        
        int rowDiff = Math.abs(target.getRow() - position.getRow());
        int colDiff = Math.abs(target.getCol() - position.getCol());
        
        // Bishop moves diagonally
        if (rowDiff != colDiff) {
            return false;
        }
        
        // Check if path is clear
        if (!board.isPathClear(position, target)) {
            return false;
        }
        
        // Check if target square is empty or has enemy piece
        ChessPiece targetPiece = board.getPieceAt(target);
        return targetPiece == null || targetPiece.getColor() != this.color;
    }
}
