@echo off
echo 🔧 Setting up Quizzერა...

REM Step 1: Create lib folder if missing
if not exist lib (
    echo 📁 Creating lib directory...
    mkdir lib
)

REM Step 2: Download dependency JARs
echo ⬇️ Downloading MySQL Connector...
curl -L -o lib\mysql-connector.jar https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.4.0.jar

echo ⬇️ Downloading BCrypt...
curl -L -o lib\jbcrypt.jar https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/jbcrypt-0.4.jar

REM Step 3: Database import
echo 📦 Setting up database...

set /p DB_USER=👉 Enter your MySQL username:
set /p DB_PASS=🔑 Enter your MySQL password:

echo 🏗️ Creating database if it doesn't exist...
mysql -u %DB_USER% -p%DB_PASS% -e "CREATE DATABASE IF NOT EXISTS quizzera"

REM Step 4: Import SQL dump if file exists
if exist db\quizzera_dump.sql (
    echo 📥 Importing schema from db\quizzera_dump.sql...
    mysql -u %DB_USER% -p%DB_PASS% quizzera < db\quizzera_dump.sql
    echo ✅ Database imported successfully!
) else (
    echo ⚠️ WARNING: SQL dump file not found! Skipping import.
)

echo 🎉 Setup complete! You can now run the project from IntelliJ.
pause
