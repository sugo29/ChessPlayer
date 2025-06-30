package com.chess.model.pieces;

import com.chess.model.*;

/**
 * King piece implementation
 */
public class King extends ChessPiece {
    
    public King(PieceColor color, Position position) {
        super(color, PieceType.KING, position);
    }
    
    @Override
    public boolean isValidMove(Position target, ChessBoard board) {
        if (target.equals(position)) {
            return false;
        }
        
        int rowDiff = Math.abs(target.getRow() - position.getRow());
        int colDiff = Math.abs(target.getCol() - position.getCol());
        
        // King can move one square in any direction
        if (rowDiff <= 1 && colDiff <= 1) {
            ChessPiece targetPiece = board.getPieceAt(target);
            return targetPiece == null || targetPiece.getColor() != this.color;
        }
        
        // TODO: Implement castling logic
        return false;
    }
}
