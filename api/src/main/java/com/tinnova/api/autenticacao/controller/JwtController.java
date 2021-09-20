package com.tinnova.api.autenticacao.controller;

import com.tinnova.api.autenticacao.DTO.JwtResponse;
import com.tinnova.api.autenticacao.config.JwtTokenUtil;
import com.tinnova.api.autenticacao.config.JwtUserDetailsService;
import com.tinnova.api.autenticacao.forms.JwtRequest;
import com.tinnova.api.gestor.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth")
public class JwtController {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> auth(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        jwtUserDetailsService.authenticate(
                authenticationRequest.getEmail().trim(),
                authenticationRequest.getSenha().trim());

        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getEmail().trim());

        final String token = jwtTokenUtil.generateToken(userDetails);
        final String name = managerRepository
                .findByEmail(authenticationRequest.getEmail().trim()).orElse(null).getNome();

        return ResponseEntity.ok().body(new JwtResponse(name,token));
    }
}
