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
