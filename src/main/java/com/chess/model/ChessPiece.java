package com.chess.model;

/**
 * Abstract base class for all chess pieces implementing common functionality
 */
public abstract class ChessPiece {
    protected PieceColor color;
    protected PieceType type;
    protected Position position;
    protected boolean hasMoved;
    
    public ChessPiece(PieceColor color, PieceType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
        this.hasMoved = false;
    }
    
    public PieceColor getColor() {
        return color;
    }
    
    public PieceType getType() {
        return type;
    }
    
    public Position getPosition() {
        return position;
    }
    
    public void setPosition(Position position) {
        this.position = position;
        this.hasMoved = true;
    }
    
    public boolean hasMoved() {
        return hasMoved;
    }
    
    /**
     * Abstract method to be implemented by each piece type
     * Returns true if the move from current position to target is valid
     */
    public abstract boolean isValidMove(Position target, ChessBoard board);
    
    /**
     * Get the symbol representation of the piece
     */
    public String getSymbol() {
        String symbol = type.getSymbol();
        return color == PieceColor.WHITE ? symbol : symbol.toLowerCase();
    }
    
    @Override
    public String toString() {
        return color + " " + type + " at " + position;
    }
}
