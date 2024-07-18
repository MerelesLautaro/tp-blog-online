package com.lautadev.tp_blog_online.service;

import com.lautadev.tp_blog_online.model.UserSec;
import com.lautadev.tp_blog_online.repository.IUserSecRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService implements OAuth2UserService {
    @Autowired
    private IUserSecService userSecService;

    @Autowired
    private IUserSecRepository userSecRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // Extraer los datos del usuario
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String firstName = (String) attributes.get("given_name");
        String lastName = (String) attributes.get("family_name");

        System.out.println("EMAIL: "+email+" firstName: "+firstName+" lastname: "+lastName);

        // Guardar o actualizar el usuario en la base de datos
        UserSec userSec = userSecRepository.findByEmail(email);
        if (userSec == null) {
            userSec = new UserSec();
            userSec.setEmail(email);
            userSec.setName(firstName);
            userSec.setLastname(lastName);
            userSecService.saveUser(userSec);
        }

        // Devolver el usuario autenticado
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "name"); // "name" es el atributo principal que representa el nombre del usuario
    }
}
