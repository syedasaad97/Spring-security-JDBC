# Authentication Assignment 

### Tech stack used:
Java 8 <br />
Java Spring Boot <br />
Spring Security <br />
JWT Authentication <br />
H2 Database <br />


### Authentication Application

Using JWT Spring Security for authentication And Authorization <br />
Login request authenticated on ``AuthenticationController.authenticateRequest`` and JWT token is generated <br /> 
In every requests token passed and validated on ``JWTFilter`` through filter and authorized. <br />

JWT token is passed by HEADERS in Authorization Key. <br />



## Requests

### USER test data Information
Users.csv file contains users data <br />
roles.csv file contains all roles <br />
user_roles.csv file contains user roles data <br />


#### Note:
I have also added postman requests in folder.

### Login Request
``
curl --location --request POST 'localhost:8080/api/authenticate' \
--header 'Content-Type: application/json' \
--data-raw '{
"userLogin":"test@gmail.com",
"password":"1234"
}'
``

### Fetch Current login User detail (ADMIN or USER role can access) Request
``
curl --location --request GET 'localhost:8080/api/user' \
--header 'Authorization: Bearer ey'
``

### Fetch All User details (ADMIN role can access) Request
``
curl --location --request GET 'localhost:8080/api/user/all' \
--header 'Authorization: Bearer ey'
``
#### Startup with Profile settings
DEV , PROD profile <br />
Default profile : DEV
##### Build jar
``
mvn clean install
``
##### Default profile (DEV), H2 database
``
mvn spring-boot:run
``

or

``
java -jar -Dspring.profiles.active=prod target/assignment-0.0.1-SNAPSHOT.jar
``




