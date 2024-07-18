package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    public void saveAccount(Account account);
    public List<Account> getAccounts();
    public Optional<Account> findAccount(Long id);
    public void deleteAccount(Long id);
    public void editAccount(Account account);

    String encriptPassword(String password);
}
