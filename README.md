# Getting Started
A simple Spring Boot application with junit5, data-jpa and PostgreSQL.

# Setup the Database
    postgres=# create database kugel;
    CREATE DATABASE
    postgres=# create role kugel login;
    CREATE ROLE
    postgres=# grant all privileges on database kugel to kugel;
    GRANT
# Create the project
    mkdir kugel
    cd kugel
    spring init --build=gradle --java-version=11 --dependencies=web,data-jpa,postgresql --groupId=com.peltops --name=kugel --artifactId=kugel -x
# Setup application.properties
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:postgresql://${PG_HOST:localhost}:5432/kugel
    spring.datasource.username=kugel
    spring.datasource.password=
# Test application context
    gradle build