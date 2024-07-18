package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.UserSec;
import com.lautadev.tp_blog_online.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSecService implements IUserSecService {
    @Autowired
    private IUserSecRepository userSecRepository;

    @Override
    public void saveUser(UserSec userSec) {
        userSecRepository.save(userSec);
    }

    @Override
    public List<UserSec> getUsers() {
        return userSecRepository.findAll();
    }

    @Override
    public Optional<UserSec> findUser(Long id) {
        return userSecRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userSecRepository.deleteById(id);
    }

    @Override
    public void editUser(UserSec userSec) {
        this.saveUser(userSec);
    }

    @Override
    public void handleOAuth2Login(@AuthenticationPrincipal OidcUser principal) {
        String email = principal.getAttribute("email");
        String givenName = principal.getAttribute("given_name");
        String familyName = principal.getAttribute("family_name");

        Optional<UserSec> existingUser = userSecRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            UserSec newUser = new UserSec();
            newUser.setEmail(email);
            newUser.setName(givenName);
            newUser.setLastname(familyName);
            this.saveUser(newUser);
        }
    }
}
