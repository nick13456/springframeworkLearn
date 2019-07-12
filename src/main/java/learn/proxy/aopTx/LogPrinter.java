package learn.proxy.aopTx;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogPrinter {

    @Pointcut(value="execution(* learn.proxy.aopTx.*.*(..))")
    public void pc1(){}

    /*@Before("pc1()")
    public void beforeLog(){
        System.out.println("打印了日志---前置通知");
    }
    @AfterReturning("pc1()")
    public void afterReturnPrintLog(){
        System.out.println("打印了日志---后置通知");
    }
    @AfterThrowing("pc1()")
    public void afterThrowingPrintLog(){
        System.out.println("打印了日志---异常通知");
    }
    @After("pc1()")
    public void afterPrintLog(){
        System.out.println("打印了日志---最终通知");
    }*/

    @Around("execution(* learn.proxy.aopTx.*.*(..))")
    public Object aroundPrintLog(ProceedingJoinPoint pjp){

        Object rtValue = null;
        try {
            System.out.println("打印了日志---环绕日志---前置");
            Object[] args = pjp.getArgs();
            rtValue = pjp.proceed(args);
            System.out.println("打印了日志---环绕通知---后置");
            return rtValue;
        }catch (Throwable t){
            System.out.println("打印了日志---环绕通知---异常");
            throw new RuntimeException(t);
        }finally {
            System.out.println("打印了日志---环绕通知---最终");
        }
    }
}
