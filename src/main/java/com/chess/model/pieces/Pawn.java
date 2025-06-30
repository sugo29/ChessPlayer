package com.chess.model.pieces;

import com.chess.model.*;

/**
 * Pawn piece implementation
 */
public class Pawn extends ChessPiece {
    
    public Pawn(PieceColor color, Position position) {
        super(color, PieceType.PAWN, position);
    }
    
    @Override
    public boolean isValidMove(Position target, ChessBoard board) {
        if (target.equals(position)) {
            return false;
        }
        
        int direction = (color == PieceColor.WHITE) ? -1 : 1;
        int rowDiff = target.getRow() - position.getRow();
        int colDiff = Math.abs(target.getCol() - position.getCol());
        
        ChessPiece targetPiece = board.getPieceAt(target);
        
        // Forward move
        if (colDiff == 0) {
            // One square forward
            if (rowDiff == direction && targetPiece == null) {
                return true;
            }
            // Two squares forward from starting position
            if (!hasMoved && rowDiff == 2 * direction && targetPiece == null) {
                return true;
            }
        }
        // Diagonal capture
        else if (colDiff == 1 && rowDiff == direction) {
            if (targetPiece != null && targetPiece.getColor() != this.color) {
                return true;
            }
            // TODO: Implement en passant
        }
        
        return false;
    }
}
