# Accounts API with CRUD operations

Exam for **Stokkur position**

This project includes API endpoints for CRUD operations over an Account Entity. 

# Requirements
1. Java 8+
2. Maven or the one included on Intellij IDEA IDE
3. Mysql Server

# Configuration

## Clone the project:
```bash
git clone git@github.com:wisog/accounts.git
```

## Set the database connection url on file:
```bash
https://github.com/wisog/accounts/blob/master/src/main/resources/application.properties
```

# Run project
```bash
mvn spring-boot:run
```

and the project will be available on 
```bash
http://localhost:8080
```

# Documentation
An API documentation web page is available on
```bash
http://localhost:8080/swagger-ui.html
```

# Use
The API have Basic Authentication for POST, PUT and DELETE requests
User & Password are:
```bash
user: root
pwd: toor 
```
Any GET request is permited.

# Docs

```bash
accounts - src
├── com.stokkur.exam.demoAccounts/ - Java source code.
|   ├── config/ - System Security & Swagger configurations.
|   ├── dto/ - The Data Transfer Object classes using Jackson annotations
|   ├── model/ - Entities for model objects
|   ├── repository/ - Spring Crus Repositories for data Access
|   ├── rest/ - Parent package for REST services code
|   |   ├── util/ - Custom classes for all services
|   ├── service/ - Parent package for the services logic
|   |   └── util/ - Util classes as Helpers for services including objects mapping
|   |   └── impl/ - Classes implementing service logic

```