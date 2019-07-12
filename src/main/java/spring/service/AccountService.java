package spring.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findById(Integer id);
    void deleteById(Integer id);
    void save(Account account);
    void updateAccount(Account account);
    void transfer(String sourceName,String TargetName,float money);
    Account findByName(String name);
}
