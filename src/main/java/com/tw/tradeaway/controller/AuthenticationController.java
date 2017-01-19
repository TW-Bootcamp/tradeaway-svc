package com.tw.tradeaway.controller;


import com.tw.tradeaway.security.JwtAuthenticationRequest;
import com.tw.tradeaway.security.JwtAuthenticationResponse;
import com.tw.tradeaway.security.JwtTokenUtil;
import com.tw.tradeaway.security.JwtUser;
import com.tw.tradeaway.service.token.UserVerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserVerificationTokenService tokenService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        GrantedAuthority authority = (GrantedAuthority) userDetails.getAuthorities().toArray()[0];

        // Return the token
        return ResponseEntity.ok(new JwtAuthenticationResponse(token, authority.getAuthority()));
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }


    @RequestMapping(value = "/auth/verify",method = RequestMethod.POST)
    public ResponseEntity validateToken(@RequestBody Map<String,String> payload){
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(payload.get("username"));
        if(tokenService.validate(user,payload.get("token"))){
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}
