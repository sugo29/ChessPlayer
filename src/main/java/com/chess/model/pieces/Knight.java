package com.chess.model.pieces;

import com.chess.model.*;

/**
 * Knight piece implementation
 */
public class Knight extends ChessPiece {
    
    public Knight(PieceColor color, Position position) {
        super(color, PieceType.KNIGHT, position);
    }
    
    @Override
    public boolean isValidMove(Position target, ChessBoard board) {
        if (target.equals(position)) {
            return false;
        }
        
        int rowDiff = Math.abs(target.getRow() - position.getRow());
        int colDiff = Math.abs(target.getCol() - position.getCol());
        
        // Knight moves in L-shape: 2 squares in one direction, 1 in perpendicular
        boolean isLMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        
        if (!isLMove) {
            return false;
        }
        
        // Check if target square is empty or has enemy piece
        ChessPiece targetPiece = board.getPieceAt(target);
        return targetPiece == null || targetPiece.getColor() != this.color;
    }
}
