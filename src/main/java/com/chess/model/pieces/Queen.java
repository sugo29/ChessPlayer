package com.chess.model.pieces;

import com.chess.model.*;

/**
 * Queen piece implementation
 */
public class Queen extends ChessPiece {
    
    public Queen(PieceColor color, Position position) {
        super(color, PieceType.QUEEN, position);
    }
    
    @Override
    public boolean isValidMove(Position target, ChessBoard board) {
        if (target.equals(position)) {
            return false;
        }
        
        int rowDiff = Math.abs(target.getRow() - position.getRow());
        int colDiff = Math.abs(target.getCol() - position.getCol());
        
        // Queen moves like rook (straight) or bishop (diagonal)
        boolean isStraightMove = (target.getRow() == position.getRow() || 
                                 target.getCol() == position.getCol());
        boolean isDiagonalMove = (rowDiff == colDiff);
        
        if (!isStraightMove && !isDiagonalMove) {
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
