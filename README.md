# AlleTemp Back

### This is a rewrite from a NodeJs project using Springboot and Mongo;

The project is simple, we read temperatures from an IOT ESP using MQTT connection
then we post to the database (No SQL). We separate Temps by days.

After that we have another collection where we extract samples and we use Machine Learning 
to predict the temperatures.

### Stack
* Springboot
* Java 23 (Was supposed to use 17, but I accidentally installed the wrong version) 
* Mongo (Cloud, later will be changed)
* Junit
* Mockito
* TensorFlow
* Lombok (Currently not used)

### Requirements

* Java 23 Corretto
* Maven
* Mongo community (Migrating from the cloud...)

### Setup 
* Create a file called _application.properties_ in the _src/main/resources_
* Add the fields: 

_spring.application.name_ 

=> _server.port_ 

=> _spring.data.mongodb.uri_

=> _spring.data.mongodb.database_

### Two ways of Compiling 

#### Docker Approach Mac ARM (M1, M2, M3, M4):
        * brew install docker 
        * brew install colima (because you're not a wanker to use GUI)
        * docker-compose up --build (This will create the Mongo Image) or
        * mvn clean package (to make sure youre using the last build)
        * docker build -t alletemp .
        * docker run -p 8080:8080 -p 5005:5005 alletemp
Make sure you have 5005:5005 otherwise debugging will not work
Configuring the Debugger in IntelliJ

            * Open IntelliJ
            * Go to Run
            * Edit configurations
            * Select Remote JVM Debug
            * Save
            * Add Breakpoints and test

#### Manual Approach
        * mvn clean install
        * java -jar target/demo-0.0.1-SNAPSHOT.jar
        * Or use IntelliJ
        * Edit configurations
        * Select springboot
        * Save (must confirm further steps)

#### Connecting to NoSql Booster
After you ran the commands above it is important to connect to NoSql Booster to visualise the Collections
        
        * Open No SQL Booster
        * Connect to localhost:27018 (if youre using docker)
        * If you followed the Manual approach connect to the default mongodb port 27017
        * If you have more instances connected to the one that you set up

## !!!!Important!!!!

#### The CPP code for the IOT is provided in another repo, the code is being rewritten (trying) in C (Bare Metal)