package spring.dao;

import spring.domain.Account;

import java.util.List;

public interface AccountDao {


    List<Account> findAll();
    Account findById(Integer id);
    void deleteById(Integer id);
    void save(Account account);
    void updateAccount(Account account);
    Account findByName(String name);


}
