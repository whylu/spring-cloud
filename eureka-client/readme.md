## Build
eureka-server
```sh
cd eureka
mvn clean package
docker build -t eureka-server .
```

build eureka-client
```sh
cd eureka-client
mvn clean package
docker build -t eureka-client .
```



## Run

create docker network for test
```sh
docker network create --driver bridge eureka
```

start eureka-server
```sh
docker run --name eureka-server -p 8761:8761 --network eureka eureka-server
```

start 'service1' for 2 instance
```sh
docker run --name eureka-client1-1 -p 18081:8080 --network eureka eureka-client \
--spring.application.name=service1 --hello.message=yooooo1-1
docker run --name eureka-client1-2 -p 18082:8080 --network eureka eureka-client \
--spring.application.name=service1 --hello.message=yooooo1-2
```
start 'service2' for 2 instance
```sh
docker run --name eureka-client2-1 -p 28081:8080 --network eureka eureka-client \
--spring.application.name=service2 --hello.message=yooooo2-1
docker run --name eureka-client2-2 -p 28082:8080 --network eureka eureka-client \
--spring.application.name=service2 --hello.message=yooooo2-2
```


## Test
```sh
curl "http://localhost:18081/hello/ming01"
curl "http://localhost:18081/service-instances/service1"
```


## Question
- If `eureka-client1-2` shutdown, eureka-client1-2 instanceInfo still listed in discoveryClient.getInstances


