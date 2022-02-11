mvn clean package
#java -jar payara-micro.jar --deploy target/*.war --nocluster --port 9091 #payara
java -jar target/dependency/webapp-runner.jar --port 9091 target/*.war #tomcat lite