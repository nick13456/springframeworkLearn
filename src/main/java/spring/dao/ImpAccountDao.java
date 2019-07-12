package spring.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import spring.domain.Account;
import spring.utils.ConnectionUtil;
import spring.utils.TransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

@Repository("impAccountDao")
public class ImpAccountDao implements AccountDao{


    @Autowired
    private ConnectionUtil connectionUtil;

    @Autowired
    private DataSourceTransactionManager springTxManager;

    @Autowired
    private DataSource dataSource;

    @Resource(name="queryRunner")
    private QueryRunner runner;

    @Autowired
    private JdbcTemplate jt;


    public List<Account> findAll() {
        try {
            return runner.query("select * from account", new BeanListHandler<Account>(Account.class));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public Account findById(Integer id) {
        try{
            return runner.query("select * from account where id=?",new BeanHandler<Account>(Account.class),id);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void deleteById(Integer id) {
        try{
            runner.update("delete * from account where id=?",id);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void save(Account account) {
        try{
            runner.update("insert into account(name,money)values(?,?)",account.getName(),account.getMoney());
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void updateAccount(Account account) {
        /**
         这个方法是dbutils框架的方法
         */
        try{
            runner.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
        }catch (Exception e){
            throw new RuntimeException();
        }

        /**
         * 下面是jdbtTemplate的方法，可以直接使用spring的事务管理器
         */
//        jt.update("update account set money=?,name=? where id=?",account.getMoney(),account.getName(),account.getId());


    }

    public Account findByName(String name) {

        /**
            这个方法是dbutils框架的方法
         */
        try {
            return runner.query(connectionUtil.getConnection(),"select * from account where name=?", new BeanHandler<Account>(Account.class), name);

        }catch (Exception e){
            throw new RuntimeException();
        }

        /**
         * 下面是jdbtTemplate的方法，可以直接使用spring的事务管理器
         */
        /*
        List<Account> accounts = jt.query("select * from account where name=?",new BeanPropertyRowMapper<Account>(Account.class),name);
        return accounts.get(0);
        */
    }

}
