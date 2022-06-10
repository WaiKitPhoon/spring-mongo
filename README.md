# Simple Spring MongoDB Application

## Prerequisites

- Java 11
- Maven
- Docker

### Starting Application

1. Clone the project
2. Run `docker run -d -p 27017:27017 --name local-mongo -v mongo-data:/data/db mongo:latest
   ` to mount a mongoDB instance to the volume. `local-mongo` can be changed to any name
3. Run mvn clean package in root directory
4. Start application using your IDE from `MongoApplication`
5. OpenAPI-Swagger documentation will be at `localhost:8585/swagger-ui/index.html`


## TODO 
- Multiple database connection
- Dockerizing the Spring application