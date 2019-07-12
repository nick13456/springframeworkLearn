package spring.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.event.TransactionalEventListener;
import spring.utils.TransactionManager;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

//@Configuration   //当父类配置configuration使用import导入子类注解时，@Configuration可以不写。
public class JdbcConfiguration {

    @Value("${jdbcUser}")
    private String name;

    @Value("${jdbcPassword}")
    private String password;

    @Value("${jdbcUrl}")
    private String url;

    @Value("${jdbcDriver}")
    private String driver;

    @Autowired
    @Qualifier("transactionManager")
    private DataSourceTransactionManager springTxManager;

    @Bean(name="queryRunner")
    public QueryRunner createQueryRunner(DataSource  dataSource) {
        /*如果持久层使用的是threadLocal中的connection，则不需要再注入datasource了。*/
        return new QueryRunner(dataSource);
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
    public JdbcTemplate getJT(DataSource  dataSource){
        return new JdbcTemplate(dataSource);
    }
}
