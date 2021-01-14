# Run

run config server
```sh
mvn spring-boot:run
```

and try:
```sh
curl http://localhost:8888/a-bootiful-client/default
curl http://localhost:8888/a-bootiful-client/prod
curl http://localhost:8888/a-bootiful-client/uat
```