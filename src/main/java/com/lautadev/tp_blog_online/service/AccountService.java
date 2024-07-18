package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.Account;
import com.lautadev.tp_blog_online.model.Roles;
import com.lautadev.tp_blog_online.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IRolesService rolesService;

    @Override
    public void saveAccount(Account account) {
        Set<Roles> roleList = new HashSet<>();
        Roles readRole;

        account.setPassword(this.encriptPassword(account.getPassword()));

        for(Roles role: account.getRolesList()){
            readRole = rolesService.findRole(role.getId()).orElse(null);
            if(readRole!=null){
                roleList.add(readRole);
            }
        }

        if(!roleList.isEmpty()){
            account.setRolesList(roleList);
            accountRepository.save(account);
        }
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findAccount(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void editAccount(Account account) {
        this.saveAccount(account);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
