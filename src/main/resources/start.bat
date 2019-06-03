@echo off
cd "C:\wxhx\"
java -jar -Dspring.config.location=application.properties  gatePlatform.jar
@echo on