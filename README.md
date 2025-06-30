# Chess Player Web Application

A modern web-based chess game built with HTML/CSS/JavaScript frontend and Java servlet backend using data structures and object-oriented programming concepts.

## Features

### Frontend (HTML/CSS/JavaScript)
- Beautiful, responsive chess board with drag-and-drop interface
- Real-time game state updates
- Move validation and highlighting
- Game history tracking
- Modern glass-morphism UI design
- Mobile-friendly responsive layout

### Backend (Java + Servlets)
- Object-oriented chess piece design with inheritance
- 2D array data structure for board representation
- RESTful servlet API for game operations
- Session management for multiple games
- JSON-based communication
- Move validation and game logic

## Architecture

### Object-Oriented Design
```
ChessPiece (Abstract Base Class)
├── King
├── Queen  
├── Rook
├── Bishop
├── Knight
└── Pawn
```

### Data Structures Used
- **2D Array**: Chess board representation (8x8 grid)
- **ArrayList**: Move history storage
- **HashMap**: Game state information
- **Enum**: Piece types and colors

### Design Patterns
- **Strategy Pattern**: Different piece movement algorithms
- **Factory Pattern**: Piece creation
- **MVC Pattern**: Model-View-Controller separation

## Project Structure

```
ChessPlayer/
├── pom.xml                          # Maven configuration
├── src/main/
│   ├── java/com/chess/
│   │   ├── model/                   # Data models
│   │   │   ├── ChessPiece.java     # Abstract base class
│   │   │   ├── ChessBoard.java     # Board data structure
│   │   │   ├── Position.java       # Position handling
│   │   │   ├── Move.java           # Move representation
│   │   │   ├── PieceColor.java     # Color enum
│   │   │   ├── PieceType.java      # Type enum
│   │   │   └── pieces/             # Piece implementations
│   │   │       ├── King.java
│   │   │       ├── Queen.java
│   │   │       ├── Rook.java
│   │   │       ├── Bishop.java
│   │   │       ├── Knight.java
│   │   │       └── Pawn.java
│   │   ├── controller/
│   │   │   └── ChessGame.java      # Game logic controller
│   │   └── servlet/
│   │       └── ChessServlet.java   # HTTP request handler
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml             # Servlet configuration
│       ├── index.html              # Main game page
│       ├── css/
│       │   └── chess.css           # Styles
│       └── js/
│           └── chess.js            # Frontend logic
```

## API Endpoints

### GET Endpoints
- `GET /chess/board` - Get current board state
- `GET /chess/info` - Get game information
- `GET /chess/moves?position=e2` - Get valid moves for piece
- `GET /chess/history` - Get move history

### POST Endpoints
- `POST /chess/move` - Make a move (params: from, to)
- `POST /chess/new` - Start new game
- `POST /chess/reset` - Reset current game

## Setup and Installation

### Prerequisites
- Java 11 or higher
- Maven 3.6+
- Apache Tomcat 9.0+ or similar servlet container

### Build Instructions

1. **Clone the repository**
```bash
git clone <repository-url>
cd ChessPlayer
```

2. **Build with Maven**
```bash
mvn clean compile
mvn package
```

3. **Deploy to Tomcat**
```bash
# Copy the generated WAR file to Tomcat webapps directory
cp target/chess-player-1.0.0.war $TOMCAT_HOME/webapps/chess.war
```

4. **Start Tomcat**
```bash
$TOMCAT_HOME/bin/startup.sh  # Linux/Mac
# OR
$TOMCAT_HOME/bin/startup.bat  # Windows
```

5. **Access the application**
Open browser and navigate to: `http://localhost:8080/chess`

### Development Setup (VS Code)

1. **Install Java Extension Pack**
2. **Install Tomcat Extension**
3. **Configure Java Path**
4. **Run/Debug Configuration**

## Usage

### Starting a Game
1. Open the web application in your browser
2. Click "New Game" to start a fresh game
3. White player moves first

### Making Moves
1. Click on a piece to select it
2. Valid moves will be highlighted in green
3. Click on a highlighted square to move
4. The game alternates between white and black players

### Game Features
- **Move Validation**: Only legal chess moves are allowed
- **Turn Management**: Automatic player switching
- **Move History**: All moves are recorded and displayed
- **Game Reset**: Reset to starting position anytime
- **Responsive Design**: Works on desktop and mobile

## Technical Implementation

### Backend Highlights

#### Chess Piece Hierarchy
```java
public abstract class ChessPiece {
    protected PieceColor color;
    protected PieceType type;
    protected Position position;
    
    public abstract boolean isValidMove(Position target, ChessBoard board);
}
```

#### Board Data Structure
```java
public class ChessBoard {
    private ChessPiece[][] board = new ChessPiece[8][8];
    private List<Move> moveHistory = new ArrayList<>();
    
    public boolean makeMove(Move move) {
        // Move validation and execution
    }
}
```

#### Servlet API Design
```java
@WebServlet("/chess/*")
public class ChessServlet extends HttpServlet {
    // RESTful endpoints for game operations
}
```

### Frontend Highlights

#### Modern CSS Features
- CSS Grid for board layout
- Flexbox for responsive design
- CSS animations and transitions
- Glass-morphism effects
- Custom hover states

#### JavaScript Architecture
```javascript
class ChessUI {
    constructor() {
        this.board = null;
        this.selectedSquare = null;
        this.validMoves = [];
    }
    
    async makeMove(from, to) {
        // Communicate with servlet backend
    }
}
```

## Future Enhancements

### Gameplay Features
- [ ] En passant capture
- [ ] Castling (kingside/queenside)
- [ ] Pawn promotion
- [ ] Check/checkmate detection
- [ ] Stalemate detection
- [ ] Draw conditions (50-move rule, repetition)

### Technical Improvements
- [ ] WebSocket for real-time multiplayer
- [ ] Chess engine integration (AI opponent)
- [ ] Game persistence (database)
- [ ] PGN (Portable Game Notation) support
- [ ] Undo/redo functionality
- [ ] Sound effects
- [ ] Animation effects for moves

### UI/UX Enhancements
- [ ] Themes and board customization
- [ ] Piece set selection
- [ ] Touch gestures for mobile
- [ ] Keyboard shortcuts
- [ ] Accessibility improvements

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Authors

- **Your Name** - Initial work

## Acknowledgments

- Chess piece Unicode symbols
- Modern CSS design patterns
- Java servlet best practices
- RESTful API design principles
