# Spring Boot Application with Docker Compose and PostgreSQL

This repository contains a Spring Boot application configured to run with Docker Compose, leveraging PostgreSQL as the database.

## Prerequisites

Before running this project, ensure you have the following installed on your machine:

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)
- [Java JDK 11+](https://openjdk.org/projects/jdk/)

## Getting Started

Follow the steps below to get the application up and running:

### 1. Clone the Repository
```bash
git clone <repository-url>
cd <repository-name>
```

### 2. Build the Spring Boot Application
Run the following command to build the Spring Boot application:
```bash
./mvnw clean package
```
This will generate a `jar` file in the `target` directory.

### 3. Configure Environment Variables
Update the `application.yml` or `.env` file if needed to customize:
- PostgreSQL username
- PostgreSQL password
- Database name

### 4. Start the Application with Docker Compose
Run the following command to start the application and PostgreSQL:
```bash
docker-compose up --build
```
This command will build the Docker images and start the containers defined in the `docker-compose.yml` file.

### 5. Access the Application
- The Spring Boot application will be available at: `http://localhost:8080`
- PostgreSQL will be accessible on port `5435`.

## Docker Compose Overview

The `docker-compose.yml` file defines two services:

### 1. `app` (Spring Boot Application)
- Builds from the Dockerfile
- Exposes port `8080`
- Depends on the `db` service

### 2. `db` (PostgreSQL Database)
- Uses the official PostgreSQL image
- Exposes port `5435`
- Configured with default username, password, and database name from environment variables

#
- Now drop the file inside the input folder in the format given below :

```
- Header Section
  Input Variables
  1000000005,9876543218
```

Based on whether the file is valid or not it will create .ok or .nok 
file in the output folder respectively


## Stopping the Application
To stop the running application, use:
```bash
docker-compose down
```
This will stop and remove all containers, networks, and volumes created by Docker Compose.






