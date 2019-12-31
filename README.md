# employee-service

## Steps to Setup

### 1. Clone the application
```bash
https://github.com/AnujaHaranePersistent/employee-service.git
```

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
POST /authenticate         -   to authenticate user and generate token

GET /api/employee`         -   to get list of employees

POST /api/employee         -   to post employee data to server

GET /api/employee/{id}     -   to get employee with given identifier

PUT /api/employee/{id}     -   to update employee with given identifier

DELETE /api/employee/{id}  -   to delete employee with given identifier

GET /manager/{id}          -   to get list of employees who reports to same manager
```

## To run Tests
Type ```bash  mvn test ``` from the root directory of the project to run the tests.
