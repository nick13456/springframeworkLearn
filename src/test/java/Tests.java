import com.sun.xml.internal.ws.developer.UsesJAXBContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.AnnotationConfigBeanDefinitionParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import spring.config.AppConfiguration;
import spring.domain.Account;
//import spring.proxyTxFactory.TxProxy;
import spring.proxyTxFactory.TxProxy;
import spring.service.AccountService;
import spring.service.ImpAccountService;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfiguration.class)
public class Tests {

    @Autowired
    @Qualifier("impAccountService")
    //@Resource(name="impAccountService")
    private AccountService accountService;

    @Autowired
    private TxProxy txProxy = null;

    @Test
    public void testFindAll(){
       List<Account> accountList = accountService.findAll();
       for(Account account:accountList){
           System.out.println(account);
       }
    }

    /**
     *
     */
    @Test
    public void testTransfer(){
        accountService.transfer("nick","lily",1000);
    }

    /**
     * 使用动态代理执行业务层方法,增强了事务通知
     */
    @Test
    public void testTxProxy(){
        txProxy.getAccountService().transfer("nick","lily",1000);
    }

    /**
     * 以下三个方法都是利用spring的aop实现增强事务通知
     */
    @Test
    public void testAop(){
        accountService.transfer("nick","lily",1000);
    }

    @Test
    public void testAop1(){
        List<Account> accounts = accountService.findAll();
        for(Account account:accounts) {
            System.out.println(account);
        }
    }
    @Test
    public void testAop2(){
       Account account = accountService.findById(1);
        System.out.println(account);
    }


}
