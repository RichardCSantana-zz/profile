#Profile

##Introduction

   A sample of a profile and login application
   
##Repository

   The config repo used here must have the vagrant enviroment up to run

##Running 

###Using spring-boot maven plugin:

* On Windows:

```
mvnw.cmd spring-boot:run 
```

* On Linux/MacOS:

```
./mvnw spring-boot:run
```

###Using compiled version:

####Compile:
  * On Windows:

```
mvnw.cmd clean package
```

  * On Linux/MacOS:

```
./mvnw clean package
```

####Run:

```
java -jar target/profile-application-0.0.1-SNAPSHOT.jar
java -jar target/ui-profile-application-0.0.1-SNAPSHOT.jar
```

###Build on Docker:

####Compile:
* On Windows:

```
mvnw.cmd clean package docker:build
```

  * On Linux/MacOS:

```
./mvnw clean package docker:build
```