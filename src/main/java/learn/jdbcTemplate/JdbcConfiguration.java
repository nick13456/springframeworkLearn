package learn.jdbcTemplate;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration   //当父类配置configuration使用import导入子类注解时，@Configuration可以不写。
@PropertySource("classpath:jdbc.properties")
public class JdbcConfiguration {

    @Value("${jdbcUser}")
    private String name;

    @Value("${jdbcPassword}")
    private String password;

    @Value("${jdbcUrl}")
    private String url;

    @Value("${jdbcDriver}")
    private String driver;


    @Bean("jdbcTemplate")
    @Scope("prototype")
    public JdbcTemplate createJdbcTemplate(@Qualifier("dataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);

    }

    @Bean(name="dataSource")
    public DataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass(driver);
        ds.setJdbcUrl(url);
        ds.setUser(name);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public PlatformTransactionManager getTxManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
