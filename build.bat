@echo off
echo Building Chess Player Web Application...
echo.

REM Clean and compile the project
call mvn clean compile

if %errorlevel% neq 0 (
    echo Build failed!
    pause
    exit /b 1
)

echo.
echo Packaging WAR file...
call mvn package

if %errorlevel% neq 0 (
    echo Packaging failed!
    pause
    exit /b 1
)

echo.
echo Build successful!
echo WAR file created: target\chess-player-1.0.0.war
echo.
echo To deploy:
echo 1. Copy target\chess-player-1.0.0.war to your Tomcat webapps directory
echo 2. Rename it to chess.war
echo 3. Start Tomcat
echo 4. Open http://localhost:8080/chess in your browser
echo.
pause
