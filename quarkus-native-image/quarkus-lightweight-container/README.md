# Quarkus-Cheat-Sheet - How to build lightweight Alpine Linux container in Quarkus

## Classes

``` java
GreetingResource
```
Sample REST Endpoint

**quarkus.native.additional-build-args** property in **application.properties** configure native-image build parameters\
**./src/main/docker/Dockerfile.alpine** - configure container




## Build

``` 
# create native package
mvn package -Pnative

# create Alpine Linux container with binary
docker build -f src/main/docker/Dockerfile.alpine -t cheat/quarkus-lightweight-container .

# enter to container terminal
docker run -it cheat/quarkus-lightweight-container /bin/sh

# start application
./application

```
