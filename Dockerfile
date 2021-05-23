FROM maven:3.6.3-openjdk-8
EXPOSE 9023
ADD target/BankAccountManagement-1.0.0.jar /app/BankAccountManagement.jar
ENTRYPOINT ["java", "-jar", "/app/BankAccountManagement.jar"]

           

