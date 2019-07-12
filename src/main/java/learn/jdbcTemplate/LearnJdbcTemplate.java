package learn.jdbcTemplate;

import javafx.scene.chart.PieChart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.domain.Account;


import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JdbcConfiguration.class)
public class LearnJdbcTemplate extends JdbcDaoSupport {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    /**
     * 从jdbcConfiguration中自动注入带有DataSource的jdbcTemplate。
     //super.getJdbcTemplate()是父类中的方法，这样如果有多个dao类需要用到jdbcTemplate，
     //那么可以直接从同一个父类中继承过来使用，减少了代码的重复。

     @Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    */

    /**
     * 以下所有的方法都可以用dao来实现。这里只是方便测试
     */

    @Test
    public void testJdbcTemplate(){
        //JdbcTemplate jt = new JdbcTemplate(dataSource);
        //super.getJdbcTemplate()是父类中的方法，这样如果有多个dao类需要用到jdbcTemplate，那么可以直接从同一个父类中继承过来使用，减少了代码的重复。
        super.getJdbcTemplate().execute("insert into account(name,money)values('jr',10000)");
    }
    @Test
    public void testJdbcTemplate2(){
        super.getJdbcTemplate().update("insert into account(name,money)values(?,?)","logi",1000);
    }
    @Test
    public void testJdbcTemplate3(){
        super.getJdbcTemplate().update("update account set money=? where id=?",10000f,7);
    }
    @Test
    public void testJdbcTemplate4(){
        super.getJdbcTemplate().update("delete from account where id=?",7);
    }
    @Test
    public void testJdbcTemplate5(){
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where money>?",new BeanPropertyRowMapper<Account>(Account.class),1000);
        for(Account account:accounts){
            System.out.println(account);
        }
    }
    @Test
    public void testJdbcTemplate6(){
        List<Account> accounts = super.getJdbcTemplate().query("select * from account where id=?",new BeanPropertyRowMapper<Account>(Account.class),1);
        System.out.println(accounts.get(0));
    }

    /**
     * Integer.class用于指定返回值类型，使用Integer来接收。使用Long.class则返回Long，需要用long来接收。
     */
    @Test
    public void testJdbcTemplate7(){
       Integer num = super.getJdbcTemplate().queryForObject("select count(?) from account where money>?",Integer.class,"id",1000);
        System.out.println(num);
    }
}
