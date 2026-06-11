package com.influa.influa.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.influa.influa.dtos.user.LoginResponse;
import com.influa.influa.dtos.user.UserLoginDTO;
import com.influa.influa.security.JwtService;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponse authenticate(UserLoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dto.email(),
                dto.password()
            )
        );

        String jwtToken = jwtService.generateToken(authentication);
        long expiresIn = JwtService.JWT_EXPIRATION;

        return new LoginResponse(jwtToken, expiresIn);
    }
}