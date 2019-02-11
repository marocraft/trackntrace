# trackntrace
Spring Module for Log generation through AOP

TnT is a java framework that offers centralization and aggregation of the format of logs .
with a simple annotation you can generate logs of your class or method .
# Here is how to use TnT framework

1. Add the following dependency to your pom.xml file
   ` <dependency>
			<groupId>ma.craft.trackntrace</groupId>
			<artifactId>annotation-interceptor</artifactId>
			<version>0.0.1-SNAPSHOT</version>
	</dependency>`
2.now you have to configure your application.yml file to costumize your template format, here is an example:
`format: "{methodName: '{{methodName}}',className: '{{className}}',logLevel: '{{logLevel}}',executionTime: '{{executionTime}}' ms,logMessage: '{{logMessage}}'}"`

3. Add the annotation to your method just like this:
`
@BusinessLog(level= LogLevel.NORMAL,message="new message")
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllStudents() {
		List<Book> messages = bookService.findAllMessages();
		if (messages.isEmpty()) {
			return new ResponseEntity<List<Book>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(messages, HttpStatus.OK);
	}
`
4. then here we go we got some logs generated:
`"{methodName: sleep,className: ma.craft.trackntrace.TestService,logLevel: NORMAL,executionTime: 202 ms,logMessage: new message}"`
