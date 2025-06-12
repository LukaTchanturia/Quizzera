@echo off
echo 🔧 Setting up Quizzera...

REM Create lib folder if missing
if not exist lib (
  mkdir lib
)

REM Download dependency JARs
echo ⬇️  Downloading MySQL Connector...
curl -L -o lib\mysql-connector.jar https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-j-8.4.0.jar

echo ⬇️  Downloading BCrypt...
curl -L -o lib\jbcrypt.jar https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/jbcrypt-0.4.jar

REM Database import
echo 📦 Setting up database...
set /p DB_USER=MySQL username:
set /p DB_PASS=MySQL password:
echo 🔧 Creating and importing database...
mysql -u %DB_USER% -p%DB_PASS% -e "CREATE DATABASE IF NOT EXISTS quizzera"
mysql -u %DB_USER% -p%DB_PASS% quizzera < db\quizzera_dump.sql

echo ✅ Setup complete! You can now run the project from IntelliJ.
pause
