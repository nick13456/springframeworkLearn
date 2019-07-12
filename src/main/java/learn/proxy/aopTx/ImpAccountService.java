package learn.proxy.aopTx;

import org.springframework.stereotype.Service;

/**
 * learn.proxy中的内容都是独立测试学习aop四种类型的通知的。
 */
@Service("accountServiceAop")
public class ImpAccountService implements AccountService {
    public void findAll(){

        System.out.println("查询了所有");
        //int i = 1/0;
    }
}
