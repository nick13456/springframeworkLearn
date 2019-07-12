import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.service.AccountService;

public class TestXml {
    public static void main(String[] args) {
        ApplicationContext ac=new ClassPathXmlApplicationContext("appConfiguration.xml");

        AccountService as = ac.getBean("impAccountService",AccountService.class);

        as.transfer("nick","lily",1000);
    }
}
