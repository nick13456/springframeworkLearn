package learn.proxy.aopTx;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"learn.proxy"})
@EnableAspectJAutoProxy
public class AopConfig {



}
