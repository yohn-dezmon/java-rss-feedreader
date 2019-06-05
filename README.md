# java-rss-feedereader

An RSS Feed Reader, that I ported over from a previous Python project:
[Python RSS Feed Reader](https://github.com/yohn-dezmon/feed_aggregator_tutorial)

This project can be run by cloning the project, entering into the root directory using Terminal/Command Line
and entering the following command:

$ mvn spring-boot:run 


To create the database for this application, please see src/main/resources/notes for the SQL 
I used within mysql. 

**Before running the project you'll need to change the password for the database
within application.properties to the password you created for mysql/mariaDB.**


Technologies used (please see pom.xml)
1. Maven 
2. Spring-boot
3. Thymeleaf 
4. MariaDB
5. Hibernate
