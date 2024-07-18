package com.lautadev.tp_blog_online.service;


import com.lautadev.tp_blog_online.model.GoogleUserInfo;
import com.lautadev.tp_blog_online.model.UserSec;
import com.lautadev.tp_blog_online.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends OidcUserService {

    @Autowired
    private IUserSecRepository userSecRepository;

    @Autowired
    private IUserSecService userSecService;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        try {
            return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser){
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());

        Optional<UserSec> userSec = userSecRepository.findByEmail(googleUserInfo.getEmail());

        System.out.println("Entro en el processOidcUser");

        if(!userSec.isPresent()) {
            UserSec userSec1 = new UserSec();
            userSec1.setName(googleUserInfo.getName());
            userSec1.setLastname(googleUserInfo.getLastname());
            userSec1.setEmail(googleUserInfo.getEmail());

            userSecService.saveUser(userSec1);
        }

        return oidcUser;
    }
}
