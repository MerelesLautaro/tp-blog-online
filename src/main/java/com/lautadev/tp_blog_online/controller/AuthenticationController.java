package com.lautadev.tp_blog_online.controller;

import com.lautadev.tp_blog_online.dto.AuthLoginRequestDTO;
import com.lautadev.tp_blog_online.dto.AuthLoginResponseDTO;
import com.lautadev.tp_blog_online.service.IUserDetailsService;
import com.lautadev.tp_blog_online.service.IUserSecService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private IUserDetailsService userDetailsService;

    @Autowired
    private IUserSecService  userSecService;

    @PostMapping("/login")
    public ResponseEntity<AuthLoginResponseDTO> login (@RequestBody @Valid AuthLoginRequestDTO userRequest){
        return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
    }

    @GetMapping("/loginSuccess")
    public ResponseEntity<String> getLoginInfo(@AuthenticationPrincipal OidcUser principal){
        userSecService.handleOAuth2Login(principal);
        return ResponseEntity.ok("redirect:/test");
    }
}
