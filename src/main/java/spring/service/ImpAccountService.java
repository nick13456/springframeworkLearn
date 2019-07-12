package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.UserDataHandler;
import spring.dao.AccountDao;
import spring.dao.ImpAccountDao;
import spring.domain.Account;
import spring.utils.ConnectionUtil;
import spring.utils.TransactionManager;

import javax.annotation.Resource;
import java.util.List;

@Service("impAccountService")
//@Transactional
public class ImpAccountService implements AccountService  {

     //@Autowired
    //@Qualifier("impAccountDao")
    @Resource(name="impAccountDao")
    private ImpAccountDao accountDao;

//    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Account> findAll() {
        return accountDao.findAll();
    }

//    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Account findById(Integer id) {
        return accountDao.findById(id);

    }
//    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Account findByName(String name) {
        return accountDao.findByName(name);
    }

    public void deleteById(Integer id) {
        accountDao.deleteById(id);
    }

    public void save(Account account) {
        accountDao.save(account);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Transactional
    public void transfer(String payName, String takeName, float money) {
                Account payAccount = accountDao.findByName(payName);
                payAccount.setMoney(payAccount.getMoney() - money);
                Account takeAccount = accountDao.findByName(takeName);
                takeAccount.setMoney(takeAccount.getMoney() + money);
                accountDao.updateAccount(payAccount);
                int i = 1 / 0;
                accountDao.updateAccount(takeAccount);
    }

}
