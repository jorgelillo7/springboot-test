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

#docker
see active containers
```bash
docker ps
```
build image
```bash
docker build --tag=studenttest .
```
see images
```bash
docker images
```
run image (sync)
```bash
docker run -p 8080:8080 studenttest
```
run image (async)
```bash
docker run -d --name springapi -p 8080:8080 studenttest
```
logs
```bash
docker logs -f springapi
```

#h2:
console -> http://localhost:8080/h2-console

jdbc url -> use: jdbc:h2:mem:testdb

#Swagger:
- check is ok -> http://localhost:8080/v2/api-docs
- swagger ui -> http://localhost:8080/swagger-ui.html

#jacoco coverage:
- first launch 
```bash
mvn test
```
- go to `target/site/jacoco/index.html`
you will see a report of your code coverage

- in `pom.xml` you can set the % of minumun coverage acceptable:
```
<limit implementation="org.jacoco.report.check.Limit">
      <minimum>75%</minimum>
</limit>
```

## Paths
You can use the collection `Spring Boot Template.postman_collection.json` from `src/main/resources`
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
