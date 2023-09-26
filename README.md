# challenge

# How to run
* Open in http://localhost:8080 
* You must have a locally installed PostgreSQL or use Docker and point it to port 5432.
Or use docker-compose up to lift docker images and PageAdmin. Open: http://localhost:16543/


# Architecture
API Rest.  Controller, Service and Repository

# About Development
Developing a REST API using Java 17, Spring Boot, JPA, Postgres, DTOs, and Exception Handlers offers several benefits and enables efficient and scalable development of web applications. 
Here's a summary of how and why you would use these technologies:

* Java 17: Java is a widely-used programming language known for its versatility and robustness.
Java 17 introduces new features and enhancements that can improve developer productivity and application performance.

* Spring Boot: Spring Boot is a popular framework for building Java-based applications, especially web applications.
It provides a streamlined development experience by simplifying configuration and offering a wide range of built-in features and libraries.
Spring Boot takes care of many common tasks, such as dependency management, auto-configuration, and embedded server setup, allowing developers to focus on application logic.

* JPA (Java Persistence API): JPA is a Java specification for accessing, managing, and persisting data in relational databases.
It provides an object-relational mapping (ORM) approach, allowing developers to work with Java objects instead of writing SQL queries directly.
JPA reduces boilerplate code and provides a standardized way to interact with databases, making data access more efficient and maintainable.

* Postgres: Postgres, or PostgreSQL, is a powerful open-source relational database management system (RDBMS).
It offers advanced features, scalability, and reliability, making it a popular choice for many applications.
Using Postgres with JPA allows you to map Java objects to database tables and perform CRUD (Create, Read, Update, Delete) operations efficiently.

* DTOs (Data Transfer Objects): DTOs are objects used for transferring data between different layers of an application, such as between the API and the database.
They serve as a contract between the API and the client, defining the data structure and format.
DTOs help decouple the internal representation of data from the external API representation, enabling better control over the data exposed by the API.

* Exception Handlers: Exception handling is crucial in any application to handle errors gracefully and provide meaningful responses to clients.
Exception handlers in Spring Boot allow you to intercept and handle exceptions, customize error messages, and return appropriate HTTP status codes.
By using exception handlers, you can centralize error handling logic and improve the overall robustness and user experience of your API.

Overall, developing a REST API using Java 17, Spring Boot, JPA, Postgres, DTOs, and Exception Handlers provides a solid foundation for building robust, scalable, and maintainable web applications.
These technologies offer a wide range of features and tools that simplify development tasks, improve performance, and enhance the overall developer experience.
