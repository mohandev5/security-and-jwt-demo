package com.example.demo.controller;

import com.example.demo.dto.AuthRequests;
import com.example.demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/hello")
    public String hi(){
        return "hello world!";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/private")
    public String noAccess(){
        return "you do not have access for this method";
    }



    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthRequests authRequests){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequests.getName(),authRequests.getPassword()));
        if(authentication.isAuthenticated()){
            return  jwtService.generateToken(authRequests.getName());
        }else {
            throw new UsernameNotFoundException("invalid username");
        }
    }

}
