# 🚀 Deploy Chess Player to Heroku

This guide will help you deploy your chess application to Heroku without installing Maven locally!

## Prerequisites

1. **Git** - [Download from git-scm.com](https://git-scm.com/download/win)
2. **Heroku CLI** - [Download from devcenter.heroku.com](https://devcenter.heroku.com/articles/heroku-cli)
3. **Heroku Account** - [Sign up at heroku.com](https://signup.heroku.com/)

## 🎯 Quick Deployment Steps

### 1. Install Heroku CLI
Download and install the Heroku CLI from: https://devcenter.heroku.com/articles/heroku-cli

### 2. Open PowerShell and navigate to your project
```powershell
cd "c:\Users\suhan\OneDrive\Documents\GitHub\ChessPlayer"
```

### 3. Initialize Git repository (if not already done)
```powershell
git init
git add .
git commit -m "Initial commit - Chess Player Web Application"
```

### 4. Login to Heroku
```powershell
heroku login
```
This will open your browser for authentication.

### 5. Create Heroku app
```powershell
heroku create your-chess-app-name
```
Replace `your-chess-app-name` with a unique name (e.g., `john-chess-player-2025`)

### 6. Deploy to Heroku
```powershell
git push heroku main
```
OR if your default branch is master:
```powershell
git push heroku master
```

### 7. Open your app
```powershell
heroku open
```

## 🎮 Your Chess App Will Be Live!

After deployment, your chess game will be available at:
`https://your-chess-app-name.herokuapp.com`

## 🔧 What Happens During Deployment

1. **Heroku detects** this is a Java Maven project
2. **Automatically downloads** Maven and Java 21
3. **Builds your project** using `mvn clean package`
4. **Creates executable JAR** with embedded Tomcat
5. **Starts your application** on Heroku's servers

## 📱 Features Available After Deployment

✅ **Full Chess Game**: Complete with all piece movements  
✅ **Real-time Gameplay**: Turn-based with move validation  
✅ **Beautiful UI**: Responsive design works on all devices  
✅ **Session Management**: Each player gets their own game  
✅ **Move History**: Track all moves in the game  
✅ **Game Controls**: New game, reset, and more  

## 🐛 Troubleshooting

### If deployment fails:
```powershell
heroku logs --tail
```

### If you need to redeploy:
```powershell
git add .
git commit -m "Update chess application"
git push heroku main
```

### Check app status:
```powershell
heroku ps
```

## 🌟 Alternative: GitHub + Heroku Auto-Deploy

1. **Push to GitHub first:**
   ```powershell
   git remote add origin https://github.com/yourusername/ChessPlayer.git
   git push -u origin main
   ```

2. **Connect GitHub to Heroku:**
   - Go to Heroku Dashboard
   - Select your app
   - Go to "Deploy" tab
   - Connect to GitHub
   - Enable automatic deploys

## 💡 Environment Variables (Optional)

If you want to customize the app:
```powershell
heroku config:set SERVER_PORT=8080
heroku config:set CHESS_THEME=classic
```

## 🎯 No Maven Required!

The beauty of this approach:
- ❌ No Maven installation needed on your laptop
- ❌ No local build process required  
- ✅ Heroku handles all building in the cloud
- ✅ Your laptop stays clean and simple
- ✅ Deploy from anywhere with just Git

## 📞 Support

If you encounter any issues:
1. Check Heroku logs: `heroku logs --tail`
2. Verify Git status: `git status`
3. Check Heroku app status: `heroku ps`

Happy Chess Playing! ♔♛♜♞♝♟
