# AlleTemp Back

### This is a rewrite from a NodeJs project using Springboot and Mongo;

The project is simple, we read temperatures from an IOT ESP using MQTT connection
then we post to the database (No SQL). We separate Temps by days.

After that we have another collection where we extract samples and we use Machine Learning 
to predict the temperatures.

### Stack
* Springboot
* Java 23 (Was supposed to use 17, but I accidentally installed the wrong version) 
* Mongo (Cloud, later will be changed
* Junit
* Mockito
* TensorFlow
* Lombok (Currently not used)

### Requirements

* Java 23 Corretto
* Maven

### Setup 
* Create a file called _application.properties_ in the _src/main/resources_
* Add the fields: 

_spring.application.name_ 

 => _server.port_ 

=> _spring.data.mongodb.uri_

=> _spring.data.mongodb.database_

## !!!!Important!!!!

#### The CPP code for the IOT is provided in another repo, the code is being rewritten (trying) in C (Bare Metal)