# Run

run config server
```sh
cd ../spring_cloud_configServer
mvn spring-boot:run
```

another cmd, cd to `spring_cloud_configClient`
```sh
mvn spring-boot:run
```

and try:
```sh
curl http://localhost:8801/hello
```