# simpleWebService
this is a simple restful webservice for IOTReport porject


---------------------------------------------------------------------------------------------
utilizing postgresql and tomcat 8.5

jersey is used as a maven library in the project
my database is postgreSQL 42.2.18 and PostgreSQL JDBC is also added to pom.xml

open JDK version 1.15

I have changed web.xml in order to oblige tomcat to accept all request from everywhere

1. Class Student.java is responsible to store data based on database schema
2. Class StudentDAO.java accesses data through JDBC Driver 
3. Class StudentService.java is the main service class utilizing Jersey annotations to implement clien-server restful web services
