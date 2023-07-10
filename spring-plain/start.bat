@ECHO OFF

SET REPO=%1

SET CLASS_PATH=%REPO%\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar
SET CLASS_PATH=%CLASS_PATH%;%REPO%\org\springframework\spring-context\5.2.7.RELEASE\spring-context-5.2.7.RELEASE.jar;
SET CLASS_PATH=%CLASS_PATH%;%REPO%\org\springframework\spring-context-support\5.2.7.RELEASE\spring-context-support-5.2.7.RELEASE.jar;
SET CLASS_PATH=%CLASS_PATH%;%REPO%\org\springframework\spring-core\5.2.7.RELEASE\spring-core-5.2.7.RELEASE.jar;
SET CLASS_PATH=%CLASS_PATH%;%REPO%\org\springframework\spring-beans\5.2.7.RELEASE\spring-beans-5.2.7.RELEASE.jar;
SET CLASS_PATH=%CLASS_PATH%;%REPO%\commons-logging\commons-logging\1.2\commons-logging-1.2.jar
SET CLASS_PATH=%CLASS_PATH%;%REPO%\org\springframework\spring-aop\5.2.7.RELEASE\spring-aop-5.2.7.RELEASE.jar
SET CLASS_PATH=%CLASS_PATH%;%REPO%\org\springframework\spring-expression\5.2.7.RELEASE\spring-expression-5.2.7.RELEASE.jar
SET CLASS_PATH=%CLASS_PATH%;.\target\spring-plain-0.0.1.jar;

java -cp %CLASS_PATH% de.ollie.springplain.Application