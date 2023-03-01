# open-weather-service
OpenWeatherService is part of an assessment.
The implementation is based on following functional requirements mentioned:
- We onboarded this software, but after an analysis from our developer team, we figured out that the software is not production ready.
- Your task is to improve all necessary points to transform this service into a production-ready service.

### Prerequisites for Development and Testing
- JDK 1.8
- Apache Maven
- Docker

### Steps to build the Service Locally
- Download or clone project from GitHub (https://github.com/srp321/open-weather-service)
- Build the application (including running the tests) by executing below command
   ```
        mvn clean install
   ```

### Steps to execute the Service Locally
- Run the application by executing command
   ```
        java -jar open-weather-service/open-weather-service-0.0.1-SNAPSHOT.jar
   ```

### Sources for API Documentation
- The documentation for the APIs as a swagger-ui html page APIs available at
  http://localhost:8080/swagger-ui.html
- The documentation for the APIs in OpenAPI json format APIs available at
  http://localhost:8080/v3/api-docs

### Steps to run application
- docker build --tag=open-weather-service:latest
- docker run open-weather-service:latest

### Additional Notes
- Few assumptions have been made regarding the data structure and use case
- Instead of using a persisting store, im-memory-storage has been used by way of a customCache implementation
- If the need is to deploy in a scaling distributed environment, and external caching can be used (such as Redis, HazelCast, etc.) to handle the latency in a better way
- Another assumption is that the city path parameter in request should be valid (as per weather-service partner) as no validation is being done on that
- Profiles (default, uat) have been used to have segregation with parameters being used in respective environments
