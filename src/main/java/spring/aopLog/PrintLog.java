package spring.aopLog;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.utils.TransactionManager;


@Component("printLog")                          //dbtutils的queryRunner需要用到ThreadLocal中的connection。
@Aspect
public class PrintLog {

    @Autowired
    private TransactionManager txManager;



    @Around("execution(* spring.service.*.*(..))")
    public Object aroundLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try {
            txManager.startTransaction();
            System.out.println("----前置通知-----");
            Object[] args = pjp.getArgs();
            rtValue = pjp.proceed(args);
            System.out.println("----后置通知---");
            txManager.commit();
            return rtValue;
        }catch (Throwable t){
            txManager.rollBack();
            System.out.println("-----异常通知----");
            throw new RuntimeException(t);
        }finally {
            txManager.release();
            System.out.println("----最终通知----");
        }
    }
}
