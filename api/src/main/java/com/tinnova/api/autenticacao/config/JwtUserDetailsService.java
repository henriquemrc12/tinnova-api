package com.tinnova.api.autenticacao.config;

import com.tinnova.api.exception.ApiException;
import com.tinnova.api.gestor.model.Manager;
import com.tinnova.api.gestor.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Manager gestor = managerRepository.findByEmail(email.trim())
                    .orElseThrow(()-> new ApiException("Usuário não encontrado com E-mail informado!"));

            return new User(gestor.getEmail(), gestor.getSenha(),
                    new ArrayList<>());
        } catch (ApiException e){
            throw e;
        }
    }

    public void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new ApiException("E-mail ou senha incorretos!");
        }
    }
}