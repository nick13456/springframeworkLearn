package spring.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 *
 * 该类和applicationConfiguration.xml作用一样
 *
 */

@Configuration
@ComponentScan(basePackages = {"spring"})
@Import({JdbcConfiguration.class,TxConfiguration.class})
@PropertySource("classpath:jdbc.properties")
//@EnableAspectJAutoProxy
@EnableTransactionManagement
public class AppConfiguration {


}
