package learn.proxy.aopTx.test;

//import learn.proxy.aop.AopConfig;
import learn.proxy.aopTx.AccountService;
import learn.proxy.aopTx.AopConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AopConfig.class)
public class AnnoTestAop {

    @Autowired
    @Qualifier("accountServiceAop")
    private AccountService accountService;

    @Test
    public void testAop(){
        accountService.findAll();
    }

}
