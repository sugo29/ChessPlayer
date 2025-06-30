# Heroku Deployment Process Explained

## 🔍 Phase 1: Project Detection
When Heroku receives your code, it scans for these files to determine project type:

- **pom.xml** → "This is a Java Maven project"
- **package.json** → "This is a Node.js project"  
- **requirements.txt** → "This is a Python project"
- **Gemfile** → "This is a Ruby project"

In your case: Heroku sees `pom.xml` and knows it's Java + Maven.

## 🛠️ Phase 2: Build Environment Setup
Heroku creates a temporary build environment with:

```
Build Environment:
├── Java 21 (from system.properties)
├── Maven 3.9.x (automatically installed)
├── Git tools
└── Build dependencies
```

## 📦 Phase 3: Dependency Resolution
Heroku runs Maven commands automatically:

```bash
# Heroku runs these commands for you:
mvn clean                    # Clean previous builds
mvn dependency:resolve       # Download all dependencies
mvn compile                  # Compile Java source code
mvn package                  # Create JAR file
```

Dependencies downloaded (from your pom.xml):
- Spring Boot Starter Web (includes embedded Tomcat)
- Jakarta Servlet API
- Gson for JSON processing
- JUnit for testing

## 🏗️ Phase 4: Application Build
Maven creates executable JAR:

```
target/
└── chess-player-1.0.0.jar (Executable JAR)
    ├── Your compiled Java classes
    ├── Static web files (HTML, CSS, JS)
    ├── Embedded Tomcat server
    ├── Spring Boot framework
    └── All dependencies bundled
```

## 🚀 Phase 5: Runtime Environment
Heroku creates a dyno (container) with:

```
Production Environment:
├── Java 21 Runtime
├── Your chess-player-1.0.0.jar
├── Environment variables (PORT, etc.)
└── Web server routing
```

## ⚡ Phase 6: Application Startup
Heroku reads your `Procfile`:

```
web: java -jar target/chess-player-1.0.0.jar
```

This command:
1. Starts Java Virtual Machine
2. Loads your JAR file
3. Spring Boot initializes embedded Tomcat
4. Your ChessServlet becomes available
5. Static files (HTML/CSS/JS) are served

## 🌐 Phase 7: URL Routing
Heroku provides:

```
https://your-app-name.herokuapp.com/
├── / → index.html (your chess board)
├── /chess/board → ChessServlet (board state)
├── /chess/move → ChessServlet (make moves)
├── /css/chess.css → Static CSS
└── /js/chess.js → Static JavaScript
```
