# Spring Boot + DynamoDB Local + Nginx API Gateway (Dockerized)

A simple local development stack for a Spring Boot REST API that connects to **DynamoDB Local**, with **Nginx** acting as a basic API gateway, everything running via Docker Compose.

## Build the Spring Boot JAR
    ./mvnw clean package

## Start the Stack
    docker compose up --build


## Access the API

- Through Nginx gateway (recommended):
  http://localhost:8080/api/greeting
- Direct to Spring Boot (for debugging):
  http://localhost:8081/api/greeting
- DynamoDB Local Admin Shell:
  http://localhost:8000/shell

## Stop everything
    docker compose down