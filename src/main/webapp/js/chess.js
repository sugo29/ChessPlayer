/**
 * Chess Player JavaScript - Frontend Game Logic
 * Handles UI interactions and communicates with Java servlet backend
 */

class ChessUI {
    constructor() {
        this.board = null;
        this.selectedSquare = null;
        this.validMoves = [];
        this.currentPlayer = 'WHITE';
        this.gameActive = true;
        
        this.pieceSymbols = {
            'WK': '♔', 'WQ': '♕', 'WR': '♖', 'WB': '♗', 'WN': '♘', 'WP': '♙',
            'BK': '♚', 'BQ': '♛', 'BR': '♜', 'BB': '♝', 'BN': '♞', 'BP': '♟'
        };
        
        this.init();
    }
    
    /**
     * Initialize the chess UI
     */
    init() {
        this.createBoard();
        this.setupEventListeners();
        this.loadGameState();
    }
    
    /**
     * Create the visual chess board
     */
    createBoard() {
        const boardElement = document.getElementById('chessBoard');
        boardElement.innerHTML = '';
        
        for (let row = 0; row < 8; row++) {
            for (let col = 0; col < 8; col++) {
                const square = document.createElement('div');
                square.className = `square ${(row + col) % 2 === 0 ? 'light' : 'dark'}`;
                square.dataset.row = row;
                square.dataset.col = col;
                square.dataset.position = this.getAlgebraicNotation(row, col);
                
                square.addEventListener('click', (e) => this.handleSquareClick(e));
                
                boardElement.appendChild(square);
            }
        }
    }
    
    /**
     * Setup event listeners
     */
    setupEventListeners() {
        document.getElementById('newGameBtn').addEventListener('click', () => this.newGame());
        document.getElementById('resetGameBtn').addEventListener('click', () => this.resetGame());
    }
    
    /**
     * Convert row/col to algebraic notation
     */
    getAlgebraicNotation(row, col) {
        return String.fromCharCode(97 + col) + (8 - row);
    }
    
    /**
     * Convert algebraic notation to row/col
     */
    getRowCol(algebraic) {
        const col = algebraic.charCodeAt(0) - 97;
        const row = 8 - parseInt(algebraic.charAt(1));
        return { row, col };
    }
    
    /**
     * Handle square click events
     */
    async handleSquareClick(event) {
        if (!this.gameActive) return;
        
        const square = event.currentTarget;
        const position = square.dataset.position;
        
        // If no square is selected, select this square if it has a piece
        if (!this.selectedSquare) {
            const piece = this.getPieceAt(position);
            if (piece && this.isPieceOwnedByCurrentPlayer(piece)) {
                this.selectSquare(square, position);
                await this.showValidMoves(position);
            }
        } else {
            // If same square clicked, deselect
            if (this.selectedSquare.dataset.position === position) {
                this.deselectSquare();
            } else {
                // Try to make a move
                await this.makeMove(this.selectedSquare.dataset.position, position);
                this.deselectSquare();
            }
        }
    }
    
    /**
     * Select a square
     */
    selectSquare(square, position) {
        this.selectedSquare = square;
        square.classList.add('selected');
    }
    
    /**
     * Deselect current square
     */
    deselectSquare() {
        if (this.selectedSquare) {
            this.selectedSquare.classList.remove('selected');
            this.selectedSquare = null;
        }
        this.clearValidMoves();
    }
    
    /**
     * Show valid moves for selected piece
     */
    async showValidMoves(position) {
        try {
            const response = await fetch(`chess/moves?position=${position}`);
            const data = await response.json();
            
            if (data.validMoves) {
                this.validMoves = data.validMoves;
                this.highlightValidMoves();
            }
        } catch (error) {
            console.error('Error fetching valid moves:', error);
        }
    }
    
    /**
     * Highlight valid move squares
     */
    highlightValidMoves() {
        this.validMoves.forEach(move => {
            const { row, col } = this.getRowCol(move);
            const square = document.querySelector(`[data-row="${row}"][data-col="${col}"]`);
            if (square) {
                square.classList.add('valid-move');
            }
        });
    }
    
    /**
     * Clear valid move highlights
     */
    clearValidMoves() {
        document.querySelectorAll('.valid-move').forEach(square => {
            square.classList.remove('valid-move');
        });
        this.validMoves = [];
    }
    
    /**
     * Make a move
     */
    async makeMove(from, to) {
        this.showLoading(true);
        
        try {
            const response = await fetch('chess/move', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: `from=${from}&to=${to}`
            });
            
            const data = await response.json();
            
            if (data.success) {
                this.updateBoard(data.board);
                this.updateGameInfo(data.gameInfo);
                this.addMoveToHistory(from + '-' + to);
                this.showMessage('Move successful!', 1000);
            } else {
                this.showMessage(data.error || 'Invalid move!', 2000);
            }
        } catch (error) {
            console.error('Error making move:', error);
            this.showMessage('Error making move. Please try again.', 2000);
        } finally {
            this.showLoading(false);
        }
    }
    
    /**
     * Update board display
     */
    updateBoard(boardState) {
        const squares = document.querySelectorAll('.square');
        
        squares.forEach(square => {
            const row = parseInt(square.dataset.row);
            const col = parseInt(square.dataset.col);
            const piece = boardState[row][col];
            
            square.innerHTML = '';
            
            if (piece) {
                const pieceElement = document.createElement('span');
                pieceElement.className = `piece ${piece.charAt(0) === 'W' ? 'white' : 'black'}`;
                pieceElement.textContent = this.pieceSymbols[piece] || '?';
                square.appendChild(pieceElement);
            }
        });
        
        this.board = boardState;
    }
    
    /**
     * Update game information display
     */
    updateGameInfo(gameInfo) {
        document.getElementById('currentPlayer').textContent = gameInfo.currentPlayer || 'WHITE';
        document.getElementById('gameStatus').textContent = gameInfo.gameState || 'ACTIVE';
        document.getElementById('moveCount').textContent = gameInfo.moveCount || 0;
        document.getElementById('lastMove').textContent = gameInfo.lastMove || '-';
        
        this.currentPlayer = gameInfo.currentPlayer || 'WHITE';
        this.gameActive = gameInfo.gameState === 'ACTIVE';
    }
    
    /**
     * Get piece at position
     */
    getPieceAt(position) {
        if (!this.board) return null;
        
        const { row, col } = this.getRowCol(position);
        return this.board[row][col];
    }
    
    /**
     * Check if piece is owned by current player
     */
    isPieceOwnedByCurrentPlayer(piece) {
        if (!piece) return false;
        const pieceColor = piece.charAt(0) === 'W' ? 'WHITE' : 'BLACK';
        return pieceColor === this.currentPlayer;
    }
    
    /**
     * Add move to history display
     */
    addMoveToHistory(move) {
        const historyList = document.getElementById('moveHistoryList');
        const moveElement = document.createElement('div');
        moveElement.className = 'history-item';
        moveElement.textContent = `${historyList.children.length + 1}. ${move}`;
        historyList.appendChild(moveElement);
        historyList.scrollTop = historyList.scrollHeight;
    }
    
    /**
     * Load initial game state
     */
    async loadGameState() {
        try {
            // Load board state
            const boardResponse = await fetch('chess/board');
            const boardData = await boardResponse.json();
            
            // Load game info
            const infoResponse = await fetch('chess/info');
            const infoData = await infoResponse.json();
            
            this.updateBoard(boardData.board);
            this.updateGameInfo(infoData);
        } catch (error) {
            console.error('Error loading game state:', error);
            this.showMessage('Error loading game. Please refresh the page.', 3000);
        }
    }
    
    /**
     * Start new game
     */
    async newGame() {
        this.showLoading(true);
        
        try {
            const response = await fetch('chess/new', { method: 'POST' });
            const data = await response.json();
            
            if (data.success) {
                this.updateBoard(data.board);
                this.updateGameInfo(data.gameInfo);
                this.clearMoveHistory();
                this.deselectSquare();
                this.showMessage('New game started!', 1500);
            }
        } catch (error) {
            console.error('Error starting new game:', error);
            this.showMessage('Error starting new game. Please try again.', 2000);
        } finally {
            this.showLoading(false);
        }
    }
    
    /**
     * Reset current game
     */
    async resetGame() {
        this.showLoading(true);
        
        try {
            const response = await fetch('chess/reset', { method: 'POST' });
            const data = await response.json();
            
            if (data.success) {
                this.updateBoard(data.board);
                this.updateGameInfo(data.gameInfo);
                this.clearMoveHistory();
                this.deselectSquare();
                this.showMessage('Game reset!', 1500);
            }
        } catch (error) {
            console.error('Error resetting game:', error);
            this.showMessage('Error resetting game. Please try again.', 2000);
        } finally {
            this.showLoading(false);
        }
    }
    
    /**
     * Clear move history display
     */
    clearMoveHistory() {
        document.getElementById('moveHistoryList').innerHTML = '';
    }
    
    /**
     * Show/hide loading indicator
     */
    showLoading(show) {
        const loadingElement = document.getElementById('loading');
        loadingElement.style.display = show ? 'flex' : 'none';
    }
    
    /**
     * Show message to user
     */
    showMessage(text, duration = 2000) {
        const messageElement = document.getElementById('message');
        const messageText = document.getElementById('messageText');
        
        messageText.textContent = text;
        messageElement.style.display = 'block';
        
        setTimeout(() => {
            messageElement.style.display = 'none';
        }, duration);
    }
}

// Initialize the chess game when page loads
document.addEventListener('DOMContentLoaded', () => {
    new ChessUI();
});
