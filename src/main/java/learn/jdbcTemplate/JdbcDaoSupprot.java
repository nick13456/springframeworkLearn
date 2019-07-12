package learn.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcDaoSupprot {
    /**
     * 让多个dao的实现类继承这个类，那么可以通过使用父类的方法来取得同一个jdbcTemplate，便可以减少代码的重复了.
     * 该jdbcTemplate已经在Configuration类中注入了datasource了。
     */
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }
}
