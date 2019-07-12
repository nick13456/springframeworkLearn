package spring.proxyTxFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import spring.domain.Account;
import spring.service.AccountService;
import spring.service.ImpAccountService;
import spring.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 该类手动实现动态代理，后期由spring的aop代替
 */
@Component
public class TxProxy {


    @Autowired
    //@Qualifier("impAccountService")   //当ioc中存在唯一类型的对象时，跳过匹配key的过程，直接按类型找对象。
    private AccountService accountService;

    @Autowired
    @Qualifier("txManager")
    private TransactionManager txManager;

    public AccountService getAccountService(){

       return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),accountService.getClass().getInterfaces(),
               new InvocationHandler() {
                   public Object invoke(Object proxy, Method method, Object[] args){
                       Object rtValue = null;
                       try {
                           txManager.startTransaction();
//                           if("transfer".equals(method.getName())) {
                               rtValue = method.invoke(accountService,args);
//                           }
                           txManager.commit();
                       }catch (Exception e) {
                           txManager.rollBack();
                           e.printStackTrace();
                       }finally {
                           txManager.release();
                       }
                       return rtValue;
                   }
                });
            }
}
