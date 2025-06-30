package com.chess.model.pieces;

import com.chess.model.*;

/**
 * Rook piece implementation
 */
public class Rook extends ChessPiece {
    
    public Rook(PieceColor color, Position position) {
        super(color, PieceType.ROOK, position);
    }
    
    @Override
    public boolean isValidMove(Position target, ChessBoard board) {
        if (target.equals(position)) {
            return false;
        }
        
        // Rook moves in straight lines (horizontal or vertical)
        if (target.getRow() != position.getRow() && target.getCol() != position.getCol()) {
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
