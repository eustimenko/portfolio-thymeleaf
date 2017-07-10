# Build and launch process

## Pre-build
Execute from `mysql` command-line the following commands:

 - `create database thymeleaf;`
 - `grant all privileges on thymeleaf.* to 'thymeleaf'@'localhost' identified by "thymeleaf";`
 
## Build, tests, running

 - Build: `./mvnw clean package`

 - Tests: `./mvnw test`

 - Running

    - Using `spring-boot-maven-plugin`: `./mvnw spring-boot:run`

    - Using `java`: `java -jar target/thymeleaf-1.0.0.jar`
 
 From Windows execute `mvnw.cmd` instead of `./mvnw`
 
 After running application go to `http://localhost:8080` from your web browser