@echo off
echo Starting Chess Player Development Server...
echo.

REM Check if Maven is available
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

echo Building project...
call mvn clean compile package -q

if %errorlevel% neq 0 (
    echo Build failed! Please check the errors above.
    pause
    exit /b 1
)

echo.
echo Project built successfully!
echo.
echo Starting embedded Tomcat server...
echo.
echo Open your browser and navigate to:
echo http://localhost:8080/chess-player
echo.
echo Press Ctrl+C to stop the server
echo.

REM Start with embedded Tomcat using Maven plugin
mvn tomcat7:run -Dport=8080

pause
