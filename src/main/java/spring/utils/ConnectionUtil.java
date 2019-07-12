package spring.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class ConnectionUtil {

    @Resource(name="dataSource")
    private DataSource dataSource;

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    public Connection getConnection(){
        try {
            Connection conn = threadLocal.get();
            if(conn == null) {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            }
            return conn;

        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public void removeConnection(){
          threadLocal.remove();

    }
}
