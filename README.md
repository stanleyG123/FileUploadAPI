== Simple File Upload API ==

== Simple file API is used to submit files and store them on the server.
user swagger ( see URL below)  for more documentation on the API

Assumptions: Java 8 and Maven are installed and configured on your machine.
- The API only submits and doesn't do deletions ( next release)
- Security is TBD ( file submitter is hardcoded to "Stan")

To build : run mvn install
To run   :  mvn spring-boot:run
To access : copy and paste this URL in your browser http://localhost:8080/swagger-ui.htm

Known issues:
- Swagger is not setting content-type correctly for multipart form submissions ( will fix next release)