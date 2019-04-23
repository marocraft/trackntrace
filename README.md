# <img src="src/docs/images/trackntrace.png" width="96" height="96"> TracknTrace Framework

## Project description

Spring Module for Log generation through AOP
TnT is a java framework that offers centralization and aggregation of the format of logs . with a simple annotation you can generate logs of your method

- Smouth exchange between DEVs and OPS
- Standardize project logs
- Save time in communication between development, deployment
  
## How to use

1.Add the following dependency to your pom.xml file 
``` xml
<dependency>
	<groupId>ma.craft.trackntrace</groupId>
	<artifactId>tnt-core</artifactId>
	<version>0.0.1-SNAPSHOT</version> 
</dependency>
```

2.Configure your application.yml file to costumize the template format :

``` json
 "{
 	methodName: '{{methodName}}',
    	className: '{{className}}',
    	logLevel: '{{logLevel}}',
    	executionTime: '{{executionTime}}' ms,
	logMessage: '{{logMessage}}'
 }"
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
- [X] Business logging: Automatic Logger for Business Layer
- [X] Rest logging: Automatic Logger for REST Layer
- [X] Data logging: Automatic Logger for Data Layer

## Maintainers
- Sallah KOKAINA
- Houseine TASSA
- Khalid ELABBADI
- Badr Eddine ZINOUN
- Sanaa HSAKOU
- Hassane EL GHACHTOUL
