package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//@Configuration            此处如果加了@Configuration那么，就无法从jdbcConfiguration中自动注入dataSource了。
public class TxConfiguration {

    @Bean(name="transactionManager")
    public DataSourceTransactionManager getTxManager(@Qualifier("dataSource") DataSource dataSource){  /* datasource 从jdbcConfiguration中自动注入*/
        return new DataSourceTransactionManager(dataSource);
    }

}
