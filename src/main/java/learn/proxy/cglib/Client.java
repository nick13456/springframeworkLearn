package learn.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Client {
    public static void main(String[] args) {
        final ImpProducer producer = new ImpProducer();
        ImpProducer cglibProducer = (ImpProducer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * @param o
             * @param method

             * @param args
             *                      以上三个和接口中参数含义一样
             * @param methodProxy
             * @return
             * @throws Throwable
             */
            public Object intercept(Object o, Method method,Object[] args, MethodProxy methodProxy) throws Throwable {

                Object value = null;

                if("sell".equals(method.getName())) {
                    Float money = (Float)args[0];
                    value = method.invoke(producer, money*0.8f);
                }

                if("sellMany".equals(method.getName())){
                    Integer num = (Integer) args[0];
                    Float money = (Float) args[1];
                    value = method.invoke(producer,num,money*0.8f);
                }
                return value;
            }
        });
        cglibProducer.sell(1000f);
        cglibProducer.sellMany(11,10000f);
    }
}
