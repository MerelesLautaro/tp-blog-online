package com.lautadev.tp_blog_online.repository;

import com.lautadev.tp_blog_online.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository  extends JpaRepository<Account, Long> {
    Optional<Account> findUserEntityByUsername(String username);
}
