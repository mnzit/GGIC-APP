mvn clean package
java -jar payara-micro.jar --deploy target/*.war --nocluster --port 9091