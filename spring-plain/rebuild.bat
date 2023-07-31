CALL mvn clean install
SET TEST_VALUE=Hello :)
java "-Da.value=Yo! A value!" -jar .\target\spring-plain-0.0.1-jar-with-dependencies.jar
ECHO %TEST_VALUE%