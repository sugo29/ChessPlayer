# 🚀 Deploy Chess Player to Render (Free!)

Render is a modern cloud platform that offers free hosting for personal projects. Perfect for your chess application!

## ✅ Why Render is Great

- **Free Tier**: 750 hours/month free hosting
- **No Credit Card**: Required only for paid features
- **Auto-Deploy**: Connects directly to GitHub
- **Zero Config**: Automatically detects Java projects
- **Always On**: Unlike Heroku, doesn't sleep (on paid plans)

## 🎯 Step-by-Step Deployment Guide

### Step 1: Push to GitHub First

```powershell
# Create a new repository on GitHub at github.com
# Then connect your local project:

git remote add origin https://github.com/yourusername/ChessPlayer.git
git branch -M main
git push -u origin main
```

### Step 2: Sign Up for Render

1. Go to **https://render.com**
2. Click "Get Started for Free"
3. Sign up with GitHub account (recommended)
4. Authorize Render to access your repositories

### Step 3: Create New Web Service

1. **Dashboard** → Click "New +"
2. **Web Service** → Select this option
3. **Connect Repository** → Choose your ChessPlayer repo
4. **Configure Service:**

```yaml
Name: chess-player-app
Environment: Java
Build Command: mvn clean package
Start Command: java -jar target/chess-player-1.0.0.jar
```

### Step 4: Environment Settings

```yaml
Runtime: Java 21
Instance Type: Free
Region: Choose closest to you
Auto-Deploy: Yes (deploys on every git push)
```

### Step 5: Deploy!

Click **"Create Web Service"** and Render will:
- Clone your repository
- Install Java 21 and Maven
- Run `mvn clean package`
- Start your application
- Provide you with a live URL

## 🔧 Render Configuration Files

### Option A: Using Web Interface (Recommended)
No additional files needed! Render auto-detects everything.

### Option B: Using render.yaml (Advanced)
If you want more control, create this file:

```yaml
# render.yaml
services:
  - type: web
    name: chess-player
    env: java
    buildCommand: mvn clean package
    startCommand: java -jar target/chess-player-1.0.0.jar
    envVars:
      - key: JAVA_TOOL_OPTIONS
        value: -Xmx512m
```

## 🌐 Your Chess App URLs

After deployment, your app will be available at:
```
https://chess-player-app.onrender.com
```

Render provides:
- **Automatic HTTPS** (SSL certificate)
- **Custom domain support** (if you have one)
- **Global CDN** for fast loading

## 📊 Free Tier Limits

- **750 hours/month** (enough for personal use)
- **512 MB RAM** per service
- **1 GB storage** 
- **100 GB bandwidth/month**
- **Auto-sleep after 15 min** (wakes up in ~30 seconds)

## 🔄 Auto-Deploy Setup

Once connected to GitHub:
1. Make changes to your code
2. Commit: `git add . && git commit -m "Update chess game"`
3. Push: `git push origin main`
4. Render automatically rebuilds and deploys!

## 🎮 What You'll Get

Your chess application will have:
- **Professional URL**: `https://your-app.onrender.com`
- **SSL Certificate**: Secure HTTPS connection
- **Global Access**: Available worldwide
- **Auto-scaling**: Handles multiple players
- **Monitoring**: Built-in logs and metrics

## 🐛 Troubleshooting

### If build fails:
1. Check Render build logs
2. Verify your `pom.xml` is correct
3. Ensure Java 21 compatibility

### If app doesn't start:
1. Check if `PORT` environment variable is used
2. Verify Spring Boot main class is correct
3. Check application logs in Render dashboard

## 💡 Pro Tips

1. **Custom Domain**: You can add your own domain later
2. **Environment Variables**: Set them in Render dashboard
3. **Database**: Add PostgreSQL if you want persistent storage
4. **Monitoring**: Render provides built-in monitoring

## 🔄 Alternative: Direct Deploy from Local

If you don't want to use GitHub:

1. Install Render CLI: `npm install -g @render/cli`
2. Login: `render login`
3. Deploy: `render deploy`

But GitHub integration is much better for ongoing development!
