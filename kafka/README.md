# Kafka

An example for using Kafka with Java (especially with Java 8).


## Requirements

Maven: 3.5.x+

Java: 8


## How to Get It Run

### Build

Build the project by typing ``mvn clean install``.


### Docker

Start the Kafka docker container using ``docker-compose`` and the ``docker.compose.yml`` in the docker folder.

* Open a shell.
* Change to folder ``docker`` in the project folder.
* Type: ``docker-compose up`` (optionally with the "-d" flag set).


### Producer

* Open a shell.
* Change to project folder.
* Type: ``java "-Dspring.kafka.bootstrap-servers=localhost:9092" "-Dlogging.level.root=ERROR" -jar .\target\kafka-test-0.0.1.jar send "--message=KLEINER-TEST"``


### Consumer

* Open a shell.
* Change to project folder.
* Type: ``java "-Dspring.kafka.bootstrap-servers=localhost:9092" "-Dlogging.level.root=ERROR" -jar .\target\kafka-test-0.0.1.jar receive``