# Docker

The hello world application can be build by:

``mvn clean install``

Thereafter a docker container can be build by:

``docker build --build-arg JAR_FILE=target/*.jar -t myorg/myapp .``

To start the build docker container, type:

``docker run -p 8080:8080 myorg/myapp``

Some alternative options are explained with

[Spring Boot Docker](https://spring.io/guides/topicals/spring-boot-docker/)