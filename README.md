# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.11/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.11/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.11/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.11/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [OpenFeign](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/)

## Currencies service
## Features
- Job synchronize exchange rate every minus
- Create, Read, Update and Delete are the four basic functions of persistent storage
## How to run
- Step 1: cd currency/
- Step 2: ```mvn clean compile package```
- Step 3: ``` docker build -t currencies-service . ```
- Step 4: ``` docker run -p 8080:8080 currencies-service ```
- Step 5: import postman collection in folder /local/currency.postman_collection.json
## Swagger-ui(http://localhost:8080/api/currency-service/docs/swagger-ui)
