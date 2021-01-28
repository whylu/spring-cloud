## Build

### wallet-service
``` shell script
cd wallet-service
mvn clean package
docker build -t wallet-service .
```

### eureka-client
```sh
cd eureka-client
mvn clean package
docker build -t eureka-client .
```

## Run
``` shell script
## start a DB for wallet service
docker run --name wallet-service-db \
-p 15432:5432 \
--network eureka \
-e POSTGRES_USER=postgresql \
-e POSTGRES_PASSWORD=postgresql \
-e POSTGRES_DB=walletservice \
 -d postgres

## start eureka-server 
docker run --name eureka-server -p 8761:8761 --network eureka -d eureka-server

## start wallet-service instance 1
docker run --name wallet-service-1 -p 18901:8901 --network eureka -d wallet-service 
## start wallet-service instance 2
docker run --name wallet-service-2 -p 18902:8901 --network eureka -d wallet-service

## start eureka-client as spot-server
docker run --name spot-server -p 18081:8080 --network eureka -d eureka-client \
--spring.application.name=spot-server

```

## Test

### Mybatis

#### Test Mybatis Only
- pure mybatis test, test mapper only
- using h2 local memory and MODE=PostgreSQL
see ming.test.cloud.mybatis.test.WalletMapperTest
    ```java
    class WalletMapperTest extends AbstTestMapper<WalletMapper> {
        public WalletMapperTest() {
            super(WalletMapper.class, "/db/migration/init-embedded.sql");
        }
    
        @Test
        void selectByUserAndCurrency() {
            Wallet wallet = getMapper().selectByUserAndCurrency("ming01", "USD");
            assertThat(wallet).isNotNull();
        }
    }
    ```

#### Test with spring
- need dependency
    ```xml
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter-test</artifactId>
                <version>2.1.3</version>
                <scope>test</scope>
            </dependency>
    ```
