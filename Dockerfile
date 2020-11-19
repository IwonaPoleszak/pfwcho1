FROM openjdk:8
add countries-mysql.jar countries-mysql.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","countries-mysql.jar"]