# Bindung External JAR Files to a Spring Boot Application

## Abstract

This blueprint shows how to bind an external JAR file to a Spring Boot application. This may be used to add classes
dynamically to an existing application, e. g. a JDBC driver.

## Example Project

The example Project contains three components:

* A main project with an application and a library module.
* A additional module which uses the library and is bound dynamically to the main project.

The additional module contains an implementation for an interface of the library. The application binds the additional
module and runs the implemtation class.

## How to Start

* Build the project via `mvn clean install`.
* Change to the folder of the app module of the main project.
* Run the app with `java -cp .\target\app-0.0.1-SNAPSHOT.jar "-Dloader.path=..\..\additionalapp\target\additionalapp-0.0.1-SNAPSHOT.jar" org.springframework.boot.loader.PropertiesLauncher`

## Important

This works a described in the code only, if the JAR file with the application is build in a way which includes a
BOOT-INF folder. If not working, check the created JAR files for the correct content.
