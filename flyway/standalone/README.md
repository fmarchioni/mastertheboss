# Flyway Standalone Demo

This is an easy example how to create database migrations using [Flyway](http://flywaydb.org/)

## Starting the Database

You can start PostgreSQL database with:

docker run -it --rm=true --name flyway_test -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -p 5432:5432 postgres:10.5;


## Execution using Maven

To execute the example just execute:

mvn clean compile flyway:migrate

## Execution using the Command Line Tool

Install the Flyway Command Line Tool according to [these instructions](http://flywaydb.org/documentation/commandline/)

Then execute the following command from the command line. The configuration file [flyway.conf](https://github.com/ThomasJaspers/db_flyway_sample/blob/master/flyway.conf) contains all the needed configuration for execution from the command line. The good thing is that Flyway already comes with JDBC drivers for [a good amount of database systems](http://flywaydb.org/documentation/commandline/) including PostgreSQL. Thus there is no need to install anything in addition here.

flyway migrate
