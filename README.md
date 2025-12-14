# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.0/maven-plugin/build-image.html)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Testing Cucumber feature](https://cucumber.io/docs)
* [Testing REST End 2 End points with Rest Assured](https://rest-assured.io/docs)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the
parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

### Running and Testing application support

Application will running under this link:

http://localhost:8080/

You can run `mvn clean install` or `mvn clean install -DskipTests` command to prebuild application,
but if you want to run test cases, then you have 2 options:

1. You simply run `mvn clean install` into the root of project. In this case all tests will run after the build.
2. You can run separate run once by once test files with Intellj. You can find files here:
   ../src/test/resources/junit-platform.properties
   And you can simply change cucumber.filter.tags=@rubic to cucumber.filter.tags=@start or @solve
   Then you will run seaprate 2 endpoint test.

3. You can test with Postman as well, just import openapi generated files into Postman:
   In this case import pls choose openapi doc version import 3.1!
   Path:
   ../openapi-doc/rubic-chip-openapi.yaml

### Three endpoint result

You can find in ../src/test/resources two directories (requests & responses).
These directories contains *.json files what are used for cucumber test input request and for check output response.

Also you can use these json files for testing for Postman or swagger-ui.
Swagger-ui url what you can reach out if you run the application:

http://localhost:8080/swagger-ui/index.html

I hope it will be fine.