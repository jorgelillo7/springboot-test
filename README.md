# springboot-test
POC, playing with Spring Boot. 

Students CRUD

# technology
- Intellij Ultimate 2018.3
- Java 11
- Spring boot
- Maven
- H2
- JPA

# Setup

Building this project:
```bash
mvn clean install
```
Start Spring Boot project:
```bash
java -jar target/tutorial-0.0.1-SNAPSHOT.jar
```

#h2:
console -> http://localhost:8080/h2-console

jdbc url -> use: jdbc:h2:mem:testdb

#Swagger:
- check is ok -> http://localhost:8080/v2/api-docs
- swagger ui -> http://localhost:8080/swagger-ui.html

## Paths
You can use the collection `Students.postman_collection.json` from `src/main/resources`
- `GET` /student

Response:
```
[
    {
        "id": 10001,
        "name": "Ranga",
        "passportNumber": "E1234567"
    },
    {
        "id": 10002,
        "name": "Ravi",
        "passportNumber": "A1234568"
    }
]
```

- `POST` /student
```
{
        "id": 1,
        "name": "Jorge",
        "passportNumber": "656565665"
}
```


....
