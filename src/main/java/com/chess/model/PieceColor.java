package com.chess.model;

/**
 * Enum representing the color of chess pieces
 */
public enum PieceColor {
    WHITE, BLACK;
    
    public PieceColor opposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
