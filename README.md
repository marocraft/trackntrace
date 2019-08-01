# <img src="src/docs/images/trackntrace.png" width="96" height="96"> TracknTrace Framework

[![Maven Central](https://img.shields.io/maven-central/v/com.github.marocraft.trackntrace/tnt-core.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.marocraft.trackntrace%22%20AND%20a:%22tnt-core%22)

## Project description

Spring Module for Log generation through AOP
TnT is a java framework that offers centralization and aggregation of the format of logs . with a simple annotation you can generate technical logs of your method

- Smouth exchange between DEVs and OPS
- Standardize project logs
- Save time in communication between development, deployment
  
## How to use

1. Add the following dependency to your pom.xml file

    ``` xml
    <dependency>
       <groupId>com.github.marocraft.trackntrace</groupId>
       <artifactId>tnt-core</artifactId>
      <version>0.2.0</version>
    </dependency>
    ```

2. Configure your application.yml or application.properties file to costumize the template format, TNT suports both .yml and .properties files so you can choose between them:

        application.properties:

        application.yml:

    ```YML
    tnt:
     logging:
      format:
       default: >
        {"methodName": "{{methodName}}",
        "className": "{{className}}",
        "logLevel": "{{logLevel}}",
        "executionTime": "{{executionTime}}",
        "logMessage": "{{logMessage}}",
        "timeStamps": "{{timeStamps}}",
        "traceId": "{{traceId}}",
        "applicationName: "{{manking}}"}
      rest: >
       {"methodName": "{{methodName}}",
       "className": "{{className}}",
       "logLevel": "{{logLevel}}",
       "executionTime": "{{executionTime}}",
       "logMessage": "{{logMessage}}",
       "timeStamps": "{{timeStamps}}",
       "httpVerb": "{{httpVerb}}",
       "httpStatus": "{{httpStatus}}",
       "httpURI": "{{httpURI}}",
       "traceId": "{{traceId}}",
       "applicationName": "my application"}
      output: json
     multithread:
      poolsize: 1
     correlation:
      traceid: x-b3-traceid
    ```

    - tnt.logging.default.format: is the log format that will be diplsayed or written

    - tnt.logging.default.output: is the output type forinstance the onely disponible type is json

    - tnt.logging.default.applicationName: is a static field, its value is the application name

    - tnt.multithread.poolsize: is the number of thread that will be started

    You can also add your own static fields such as applicationName, and yne can remove any field you want from the format


3. Add the annotation @EnableTracknTrace to your main class:

    ``` java
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.context.annotation.ComponentScan;

    import com.github.marocraft.trackntrace.annotation.EnableTracknTrace;

    @SpringBootApplication
    @EnableTracknTrace
    @ComponentScan(basePackages = { "com.organisation" })
    public class DemoTnt {
        public static void main(String[] args) {
            SpringApplication.run(DemoTnt.class, args);
        }
    }
    ```

4. Add the annotation Trace to your method

    ``` java
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.github.marocraft.trackntrace.annotation.Trace;
    import com.github.marocraft.trackntrace.domain.LogLevel;


    @RestController
    public class RestControllerTnT {
        @Trace(message="my message",level=LogLevel.IMPORTANT)
        @GetMapping(value = "/hello")
        public String getTntLogs() {
            return "works";
        }
    }
    ```

     - Parameters message and level are optional

5. Add logback.xml file to your src/main/resources:

    ``` xml
    <configuration>
        <appender name="FILE-TNT" class="ch.qos.logback.core.FileAppender">
                <file>C:/logstnt/demo-TnT-logs.txt</file>

                <encoder>
                    <Pattern>
                        %msg%n
                    </Pattern>
                </encoder>
        </appender>
        <logger name="com.github.marocraft.trackntrace" level="debug"
            additivity="false">
            <appender-ref ref="FILE-TNT" />
        </logger>
    </configuration>

    ```

    - Specify the pattern and TnT logs will be de included via variable %msg
    - Specify a new logger with this name: name="com.github.marocraft.trackntrace"

6. Result

    ``` json
    {"methodName": "getTntLogs","className": "com.organisation.framework.demotnt.RestControllerTnT","logLevel": "IMPORTANT","executionTime": "4","logMessage": "","traceId": "d769eada-8f61-496f-be3e-d623790dca59","spanId": "31d79f00-9ba8-4de8-9cad-ff84dfbf230d"}
    ```
## Release-Notes

 -Configure TnT with a .yml or .properties file.

 -Annotation @Trace to log a method.
 
 -Inoformations that will be logged by default:

	* Method name
	* Class Name
	* Execution Time
	* TimeStamps
	* Log Level
	* log message
	* correlation id
	* geoip.
	
 -HttpRest informations that will be logged:

	* Method name
	* Class Name
	* Execution Time
	* TimeStamps
	* Log Level
	* log message
	* correlation id
	* geoip
	* Http Verb
	* Http Status
	* Http URI


 - Static Fields
 
 - Nombre of threads is configurable

## Maintainers

- Sallah KOKAINA
- Housseine TASSA
- Khalid ELABBADI
- Badr Eddine ZINOUN
- Sanaa HSAKOU
- Hassane EL GHACHTOUL
