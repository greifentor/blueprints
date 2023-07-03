# Docker

The hello world application can be build by:

``mvn clean install``

Thereafter a docker container can be build by:

``docker build --build-arg JAR_FILE=target/*.jar -t myorg/myapp .``

To start the build docker container, type:

``docker run -p 8080:8080 myorg/myapp``

Some alternative options are explained with

[Spring Boot Docker](https://spring.io/guides/topicals/spring-boot-docker/)

## Fun Facts

### Dockerfile Naming

The docker file ``Dockerfile`` must be named so. Other naming leads to a not really self explaining error message:

``ERROR: failed to solve: failed to read dockerfile: open /var/lib/docker/tmp/buildkit-mount1279854336/Dockerfile: no such file or directory``

### Command Line Arguments

Command line arguments of the ``docker run`` call are automatically passed to the started application.