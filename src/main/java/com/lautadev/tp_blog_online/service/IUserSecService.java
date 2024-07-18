package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.UserSec;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.List;
import java.util.Optional;

public interface IUserSecService {
    public void saveUser(UserSec userSec);
    public List<UserSec> getUsers();
    public Optional<UserSec> findUser(Long id);
    public void deleteUser(Long id);
    public void editUser(UserSec userSec);
}
