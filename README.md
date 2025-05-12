# Seminario Unipi 2025

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

---

## Overview

This application provides a RESTful service for managing transport strategies. It uses the Strategy Pattern to dynamically select the appropriate transport strategy based on the input provided. The service supports creating, retrieving, updating, and deleting transport entities.

## Features

- **Create Transport Strategy**: Create a new transport strategy based on the provided input.
- **Retrieve Transport Entity**: Retrieve a transport entity by its ID.
- **Update Transport Entity**: Update an existing transport entity.
- **Delete Transport Entity**: Delete a transport entity by its ID.

---

## Usage

Along with this application, you will find a docker-compose.yml file that you will need to start it.</br>
By inspecting it you will find two services: a PostgreSQL database and the application itself.</br>

To try the application just compile the code through
```shell script
./mvnw package
``` 
then create the Docker image with
```shell script
docker build -f src/main/docker/Dockerfile.jvm -t seminario-unipi-2025-transport-jvm .
```
and start the compose with
```shell script
docker-compose --profile all up -d
```
This will start both the Database and the application (**depending on your version of docker the command may be "docker compose" and not "docker-compose"**).   .

Otherwise, if you just want to test your application via [Quarkus DevMode](https://quarkus.io/guides/getting-started#development-mode) you can use
```shell script
docker-compose --profile dev up -d
```
to start only the database, and then use
```shell script
./mvnw quarkus:dev
```
to start your application in live coding

to stop the docker compose you can use 
```shell script
docker-compose --profile all stop
```

to stop the docker compose and remove the containers (for a fresh start) you can use 
```shell script
docker-compose --profile all down
```
---

### Endpoints definition and examples

- **POST /transport**: Create a new transport strategy.
    - **Request Body**: `TransportInputDTO`
    - **Response**: `TransportOutputDTO`
    - **Status Code**: 201 Created
```bash
curl -X POST "http://localhost:8080/transport" -H "Content-Type: application/json" -d '{
"distanceKm": 100,
"destinationType": "NATIONAL",
"isUrgent": true,
"weightKg": 50
}'
```
- **GET /transport/{id}**: Retrieve a transport entity by ID.
    - **Path Parameter**: `id` (Long)
    - **Response**: `TransportOutputDTO`
    - **Status Code**: 200 OK
```bash
curl -X GET "http://localhost:8080/transport/1"
```
- **PUT /transport/{id}**: Update an existing transport entity.
    - **Path Parameter**: `id` (Long)
    - **Request Body**: `TransportInputDTO`
    - **Response**: `TransportOutputDTO`
    - **Status Code**: 200 OK
```bash
curl -X PUT "http://localhost:8080/transport/1" -H "Content-Type: application/json" -d '{
  "distanceKm": 150,
  "destinationType": "INTERNATIONAL",
  "isUrgent": false,
  "weightKg": 75
}'
```
- **DELETE /transport/{id}**: Delete a transport entity by ID.
    - **Path Parameter**: `id` (Long)
    - **Status Code**: 204 No Content
```bash
curl -X DELETE "http://localhost:8080/transport/1"
```

You can also visit http://localhost:8080/q/swagger-ui to explore a minimal SwaggerUI