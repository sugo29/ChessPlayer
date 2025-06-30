package com.chess.model;

/**
 * Represents a position on the chess board using coordinates
 */
public class Position {
    private final int row;
    private final int col;
    
    public Position(int row, int col) {
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            throw new IllegalArgumentException("Position must be within 0-7 range");
        }
        this.row = row;
        this.col = col;
    }
    
    public Position(String algebraic) {
        if (algebraic.length() != 2) {
            throw new IllegalArgumentException("Invalid algebraic notation");
        }
        this.col = algebraic.charAt(0) - 'a';
        this.row = 8 - (algebraic.charAt(1) - '0');
        
        if (row < 0 || row > 7 || col < 0 || col > 7) {
            throw new IllegalArgumentException("Invalid algebraic notation");
        }
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public String toAlgebraic() {
        return String.valueOf((char)('a' + col)) + (8 - row);
    }
    
    public boolean isValid() {
        return row >= 0 && row <= 7 && col >= 0 && col <= 7;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }
    
    @Override
    public int hashCode() {
        return row * 8 + col;
    }
    
    @Override
    public String toString() {
        return toAlgebraic();
    }
}
