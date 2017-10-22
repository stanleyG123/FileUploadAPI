#Simple File Upload API

The API is used to submit files and store them on the server.
use swagger ( see URL below)  for more documentation on the API.

##Assumptions:
- Java 8 and Maven are installed and configured on your machine.
- The API only adds files( no deletions or replacements)
- Security is TBD ( file submitter is hardcoded to "Stan")
- Only added integration tests for now, since there is not much business logic ,
just the glue code


__To build__ : run mvn install

__To run__   :  mvn spring-boot:run

__To access__ : copy and paste this URL in your browser http://localhost:8080/swagger-ui.html

#Known issues:
- Swagger is not setting content-type correctly for multipart form submissions ( will fix next release)
- Add some unhappy path unit tests
- Spring exception interceptor to map correct HTTP codes to various error exceptions
- Add other functionality such as " delete file" , "replace file"," replace file metadata"