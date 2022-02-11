mvn clean package
java -jar target/dependency/app-server.jar --deploy target/*.war --nocluster --port 9091 #payara
#java -jar target/dependency/app-server.jar --port 9091 target/*.war #tomcat lite