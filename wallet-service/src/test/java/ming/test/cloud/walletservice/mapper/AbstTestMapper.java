package ming.test.cloud.walletservice.mapper;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.InputStream;
import java.util.Properties;

public abstract class AbstTestMapper<T> {
    private final SqlSessionFactory sqlSessionFactory;
    private final Class<T> clazz;
    private SqlSession session;

    static {
        System.setProperty("user.timezone", "UTC");
        System.out.println("Using timezone: " + System.getProperty("user.timezone"));
    }

    protected AbstTestMapper(Class<T> clazz) {
        this(clazz, null);
    }
    protected AbstTestMapper(Class<T> clazz, String... initSqlPaths) {
        this.clazz = clazz;
        String url = "jdbc:h2:mem:;MODE=PostgreSQL;";
        if(initSqlPaths!=null) {
            StringBuilder sb = new StringBuilder(url);

            for (int i = 0; i < initSqlPaths.length; i++) {
                initSqlPaths[i] = "RUNSCRIPT FROM '" + AbstTestMapper.class.getResource(initSqlPaths[i]).getPath() + "'";
            }
            sb.append(";INIT=").append(String.join("\\;", initSqlPaths));
            url = sb.toString();
        }
        Properties properties = new Properties();
        properties.setProperty("h2.database.url", url);
        properties.setProperty("h2.database.driver", "org.h2.Driver");

        InputStream inputStream = AbstTestMapper.class.getResourceAsStream("/mybatis-config-junit.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
    }

    @BeforeEach
    public void before() {
        session = sqlSessionFactory.openSession(true);
    }

    @AfterEach
    public void after() {
        session.close();
    }

    public T getMapper() {
        return session.getMapper(clazz);
    }


}
