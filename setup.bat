@echo off
echo 🔧 Starting Quizzერა setup...

REM Create lib folder if it doesn't exist
if not exist lib (
    mkdir lib
)

echo ⬇️ Downloading MySQL Connector...
curl -L -o lib\mysql-connector.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.33/mysql-connector-java-8.0.33.jar

echo ⬇️ Downloading jBCrypt...
curl -L -o lib\jbcrypt.jar https://repo1.maven.org/maven2/org/mindrot/jbcrypt/0.4/jbcrypt-0.4.jar

echo ✅ Setup complete! Required libraries are ready in the lib folder.
pause
