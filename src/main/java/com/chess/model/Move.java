package com.chess.model;

/**
 * Represents a chess move from one position to another
 */
public class Move {
    private Position from;
    private Position to;
    private ChessPiece capturedPiece;
    private boolean isPromotion;
    private PieceType promotionPiece;
    
    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
        this.isPromotion = false;
    }
    
    public Move(String fromAlgebraic, String toAlgebraic) {
        this.from = new Position(fromAlgebraic);
        this.to = new Position(toAlgebraic);
        this.isPromotion = false;
    }
    
    public Position getFrom() {
        return from;
    }
    
    public Position getTo() {
        return to;
    }
    
    public ChessPiece getCapturedPiece() {
        return capturedPiece;
    }
    
    public void setCapturedPiece(ChessPiece capturedPiece) {
        this.capturedPiece = capturedPiece;
    }
    
    public boolean isPromotion() {
        return isPromotion;
    }
    
    public void setPromotion(boolean promotion) {
        isPromotion = promotion;
    }
    
    public PieceType getPromotionPiece() {
        return promotionPiece;
    }
    
    public void setPromotionPiece(PieceType promotionPiece) {
        this.promotionPiece = promotionPiece;
        this.isPromotion = true;
    }
    
    public boolean isCapture() {
        return capturedPiece != null;
    }
    
    @Override
    public String toString() {
        return from.toString() + "-" + to.toString();
    }
}
