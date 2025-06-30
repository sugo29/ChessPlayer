@echo off
echo Chess Player - GitHub Setup for Render Deployment
echo ================================================
echo.

echo Current Git status:
git status
echo.

echo Instructions:
echo 1. Go to github.com and create a new repository called 'ChessPlayer'
echo 2. Copy the repository URL (https://github.com/yourusername/ChessPlayer.git)
echo 3. Run the commands below (replace 'yourusername' with your GitHub username):
echo.

echo Commands to run:
echo ================
echo git remote add origin https://github.com/yourusername/ChessPlayer.git
echo git branch -M main  
echo git push -u origin main
echo.

echo After pushing to GitHub:
echo ========================
echo 1. Go to render.com
echo 2. Sign up with your GitHub account
echo 3. Create new Web Service
echo 4. Connect your ChessPlayer repository
echo 5. Use these settings:
echo    - Environment: Java
echo    - Build Command: mvn clean package
echo    - Start Command: java -jar target/chess-player-1.0.0.jar
echo.

echo Your chess game will be live at: https://your-app-name.onrender.com
echo.

pause
