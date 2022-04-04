# mediscreen
![Accueil](https://user-images.githubusercontent.com/79265943/161589072-edbfe9b2-a462-4a8f-b7c4-c8fc12571116.png)
# Home Page Application 
![HomePage](https://user-images.githubusercontent.com/79265943/161589440-78af77b6-2085-4445-9b27-c0c1ce3f29a4.png)

# Technology and Prerequisites 
* JAVA 11 JDk
* Springboot
* Mysql
* MongoDB
* Maven
* Docker

## Installing 

Install Java: - https://www.oracle.com/fr/java/technologies/javase-downloads.html

Install Maven - https://maven.apache.org/install.html

Install MySql: - https://dev.mysql.com/downloads/mysql

Intall MongoDB: - https://docs.mongodb.com/manual/administration/install-community/

Install Docker: - https://www.docker.com/products/docker-desktop

# Application Architectural

## Description : Mediscreen is composed of 4 microServices.
* Patient
* PatientHistorical
* PatientDiabetesRisk
* Ui

## MicroServices Details

### Port:9010 - Ui:
* It is the microservice which manage the user interface. It communicate with the 3 other microservices thanks to Feign( HTTP client which facilitates the Api's calls)
* To facilitate development, THYMELEAF and BOOSTRAP are used.

### Port:9011 - Patient:
* This microservice allow to process CRUD operations in order to manage Patient's informations. To do this, the API comunicates with a MYSQL database. 

### Port:9012 - PatientHistorical
* This microservice allow to process CRUD operations in order to manage DoctorNote about Patient. To do this, the API comunicates with a MONGODB database.

### Port:9013 - PatientDiabetesRisk

* This microservice defines the risk's level of a patient's diabetes. It based on the data retrived by communicating through Feign with Patient and PatientHistorical.

# Basic Architecture
![Architecture P9](https://user-images.githubusercontent.com/79265943/161589755-e5f7b1dd-b300-4395-8cf6-dc5ec0fd877c.png)
![Patient Service drawio](https://user-images.githubusercontent.com/79265943/161589861-01fe1ef7-b37b-409e-8062-11b68e8de349.png)
![PatientHistorical](https://user-images.githubusercontent.com/79265943/161589873-0aa66bfd-5224-47b7-8b12-8f48da87a751.png)
![PatientDiabetesRisk drawio (1)](https://user-images.githubusercontent.com/79265943/161589879-17b1e80f-139f-40d8-a79f-cad48a9fa73d.png)
![Interface Utilisateur](https://user-images.githubusercontent.com/79265943/161589888-c4280620-5d27-4615-9c7f-87a98d298312.png)

# Run Application 

* Install the prerequisites and Technology list above. 
* Open your terminal and go to the directory containing docker-compose.yml

:one: Docker
----
▶️ SYNTAX = docker-compose up -d


