@echo off
cd "C:\wxhx\"
java -Xms1024m -Xmx2048m -XX:PermSize=1024m -XX:MaxPermSize=2048m -XX:MaxNewSize=512m -XX:+HeapDumpOnOutOfMemoryError -jar -Dspring.config.location=application.properties  gatePlatform.jar
@echo on