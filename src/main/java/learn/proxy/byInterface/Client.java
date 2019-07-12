package learn.proxy.byInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {



    public static void main(String[] args) {

        final ImpProducer producer = new ImpProducer();
        Producer proxyProducer = (Producer) Proxy.newProxyInstance(producer.getClass().getClassLoader(), producer.getClass().getInterfaces(),
                new InvocationHandler() {
                    /**
                     *
                     * @param proxy     代理对象的引用名称
                     * @param method    当前执行的方法
                     * @param args      当前执行方法所需的参数
                     * @return          和被代理对象有相同的返回值
                     * @throws Throwable
                     */
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                            Object value = null;

                            if("sell".equals(method.getName())) {
                                Float money = (Float)args[0];
                                value = method.invoke(producer, money * 0.8f);
                            }
                            if("sellMany".equals(method.getName())){
                                Integer num = (Integer)args[0];
                                Float money = (Float)args[1];
                                value = method.invoke(producer,num,money*0.8f);
                            }
                            return value;
                    }
        });
        proxyProducer.sell(10000f);
        proxyProducer.sellMany(10,10000f);

        System.out.println("直接反射");
        try {
                System.out.println(Class.forName("learn.proxy.byInterface.ImpProducer").getMethod("sellMany", Integer.class, Float.class).invoke(producer,11,1000f));
            }catch (Exception e){
                e.printStackTrace();
        }

    }


}
