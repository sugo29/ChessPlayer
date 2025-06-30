@echo off
echo Chess Player - Quick Start Guide
echo ================================
echo.

echo Java version detected:
java -version
echo.

echo To run this chess application, you need Maven installed.
echo.
echo OPTION 1 - Install Maven (Recommended):
echo ----------------------------------------
echo 1. Download Maven from: https://maven.apache.org/download.cgi
echo 2. Extract to C:\apache-maven-3.9.5
echo 3. Add C:\apache-maven-3.9.5\bin to your PATH environment variable
echo 4. Open new command prompt and run: mvn --version
echo 5. Then run: mvn clean package cargo:run
echo.

echo OPTION 2 - Use Chocolatey (if installed):
echo -----------------------------------------
echo choco install maven
echo.

echo OPTION 3 - Use Scoop (if installed):
echo ------------------------------------
echo scoop install maven
echo.

echo OPTION 4 - Manual Java compilation (Advanced):
echo ----------------------------------------------
echo This would require manually compiling all Java files and setting up
echo a web server, which is complex. Maven is the recommended approach.
echo.

echo After Maven is installed, run these commands:
echo 1. mvn clean compile          (compile the project)
echo 2. mvn package               (create WAR file)
echo 3. mvn cargo:run             (run with embedded Tomcat)
echo.
echo Then open: http://localhost:8080/chess
echo.

pause
