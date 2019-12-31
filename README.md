# employee-service

## Steps to Setup

### 1. Clone the application

https://github.com/givanthak/spring-boot-rest-api-tutorial.git

### 2. Create Postgres database
```bash
create database employee-data
```

### 3. Change postgres username and password as per your installation

``` bash
open src/main/resources/application.yml
```

change datasource.username and datasource.password as per your postgres installation

### 4. Build and run the app using maven

```bash
mvn package
java -jar target/EmployeeDataService-0.0.1-SNAPSHOT.jar
```
Alternatively, you can run the app without packaging it using -

```bash
mvn spring-boot:run
```

The app will start running at http://localhost:8080.

# Explore Rest APIs

The app defines following CRUD APIs.

```bash
GET /api/v1/users

POST /api/v1/users

GET /api/v1/users/{userId}

PUT /api/v1/users/{userId}

DELETE /api/v1/users/{userId}
```

## To run Tests
Type mvn test from the root directory of the project to run the tests.
