# ratpack-template

## Start application locally

### Using Gradle tasks
Users can start the application locally, by using the gradle task:
```shell
./gradlew service-mocks:run
./gradlew service:run
```
check if server is up, by using the URL `localhost:5050/trolltrump/private/status`

### Using docker compose
Users can start the application locally, by using the docker-compose:
```shell
docker-compose up
```
check if server is up, by using the URL `localhost:5050/trolltrump/private/status`

and shut down the application, by using docker-compose again:
```shell
docker-compose down
```
