mvn clean package
#java -jar target/dependency/app-server.jar --port 9091 target/*.war #tomcat lite

java -jar target/dependency/app-server.jar --port 9091 --deploy target/*.war --nocluster

