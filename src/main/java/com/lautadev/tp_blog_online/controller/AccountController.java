package com.lautadev.tp_blog_online.controller;

import com.lautadev.tp_blog_online.model.Account;
import com.lautadev.tp_blog_online.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@PreAuthorize("denyAll()")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    @PreAuthorize("permitAll()")
    public ResponseEntity<String> saveAccount(@RequestBody Account account){
        accountService.saveAccount(account);
        return ResponseEntity.ok("Account saved Successfully");
    }

    @GetMapping("/get")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Account> findAccount(@PathVariable Long id){
        Optional<Account> account = accountService.findAccount(id);
        return account.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted Successfully");
    }

    @PutMapping("/edit")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Account> editAccount(@RequestBody Account account){
        accountService.editAccount(account);
        Optional<Account> accountEdit = accountService.findAccount(account.getId());
        return accountEdit.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }
}
