# Project description

Spring Module for Log generation through AOP
TnT is a java framework that offers centralization and aggregation of the format of logs . with a simple annotation you can generate logs of your method

- Contrat entre les ops et les Dev 
- Uniformiser les log du projets
- Gain de temps en communacation , developpement , déploiement
  
## Requirements

For development, you will need:

- Spring XX
- [JDK 1.8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.2+](https://maven.apache.org/download.cgi)

## Get Started

1. Clone the repo with the submodules.  

``` shell
$ git clone https://github.com/marocraft/trackntrace.git
```

2. Checkout the "develop" branch .  
   
``` shell
$ git checkout develop
```

3. Pull, build and execute.

``` shell
$ git pull
$ mvn clean install
```


## How to use

1.Add the following dependency to your pom.xml file 
``` xml
<dependency>
    <groupId>ma.craft.trackntrace</groupId> 
    <artifactId>annotation-interceptor</artifactId> 
    <version>0.0.1-SNAPSHOT</version> 
</dependency>
```

2.Configure your application.yml file to costumize the template format :

``` json
 "{methodName: '{{methodName}}',
    className: '{{className}}',
    logLevel: '{{logLevel}}',
    executionTime: '{{executionTime}}'
    ms,logMessage: '{{logMessage}}'}"
```

3.Add the annotation to your method

 ``` java
 @BusinessLog(level= LogLevel.NORMAL,message="new message")

 @RequestMapping(value = "/books", method = RequestMethod.GET) 
 public ResponseEntity<List<Book>> listAllStudents() { 
 List<Book> messages = bookService.findAllMessages(); 
 if (messages.isEmpty()) {
    return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
    }
 return new ResponseEntity<List<Book>>(messages, HttpStatus.OK);
 }
```

4.Result

``` json
 "{methodName: sleep,className: ma.craft.trackntrace.TestService,logLevel: NORMAL,executionTime: 202 ms,logMessage: new message}"
```

## Features

- [x] AutoLog
- [ ] Data : Automatic Logger for Data Layer
- [ ] Business : Automatic Logger for Business Layer
- [ ] Rest : Automatic Logger for REST Layer

## How to contribute

## Maintainers

- Sallah Kokaina
- Houseine Tassa
- Khalid ELABBADI
- Badr Eddine Zinoun
- Sanaa Hsakou
- Hassane El Ghachtoul