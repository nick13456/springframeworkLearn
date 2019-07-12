package spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 该类手动实现事务通知，后期将由spring的事务通知取代
 */
@Component("txManager")
public class TransactionManager {

    @Autowired
    private ConnectionUtil connectinUtil;

    public void startTransaction(){
        try {
            connectinUtil.getConnection().setAutoCommit(false);
        }catch (Exception e){
           e.printStackTrace();
        }
    }

    public void commit()  {
        try{
            connectinUtil.getConnection().commit();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void rollBack(){
        try{
            connectinUtil.getConnection().rollback();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void release(){
        try{
            connectinUtil.getConnection().close();
            connectinUtil.removeConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
