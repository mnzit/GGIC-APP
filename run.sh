mvn clean package
java -jar payara-micro-5.2021.10.jar --deploy target/*.war --nocluster
