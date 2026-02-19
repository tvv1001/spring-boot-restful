# test-proj

Simple Spring Boot RESTful app (Maven).

Run locally:

```bash
# build
mvn -DskipTests package

# run
mvn spring-boot:run

# or run the jar
java -jar target/test-proj-0.0.1-SNAPSHOT.jar
```

Endpoints:

- `GET /api/hello` - returns a greeting
- `POST /api/echo` - echoes JSON payload back under `received`
