# Docker

The hello world application can be build by:

``mvn clean install``

To start the build docker container, type:

``docker run -p 8080:8080 hello-docker:0.0.1-SNAPSHOT``

Some alternative options are explained with

[Spring Boot Docker](https://spring.io/guides/topicals/spring-boot-docker/)

The docker container is build by the ``com.spotify:dockerfile-maven-plugin`` automatically.

Alternatively the docker container can be build by removing the plugin for the POM file and call

``docker build --build-arg JAR_FILE=target/*.jar -t hello-docker .``

manually. In this case the container could be started by typing:

``docker run -p 8080:8080 hello-docker``

## Fun Facts

### Dockerfile Naming

The docker file ``Dockerfile`` must be named so. Other naming leads to a not really self explaining error message:

``ERROR: failed to solve: failed to read dockerfile: open /var/lib/docker/tmp/buildkit-mount1279854336/Dockerfile: no such file or directory``

### Command Line Arguments

Command line arguments of the ``docker run`` call are automatically passed to the started application.