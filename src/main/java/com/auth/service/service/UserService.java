package com.auth.service.service;

import com.auth.service.entity.User;
import com.auth.service.repository.UserRepository;
import com.auth.service.utilsSecurity.Crypt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    private final KeyPair keyPair;
    @Transactional
    public void addUser(User user){
        if(userRepository.findByLogin(user.getLogin()).isPresent())
            throw new UsernameNotFoundException("Such user already exists");
        else{
            user.setPassword(Crypt.hash(user.getPassword()));
            userRepository.save(user);
        }
    }
    @Transactional
    public String loginUser(Map<String, String> user){
        String login = user.get("login");
        Optional<User> person = userRepository.findByLogin(login);
        String password = person.get().getPassword();
        String email = person.get().getEmail();

        if (!Crypt.verifyAndUpdateHash(user.get("password"), password))
            throw new UsernameNotFoundException("Password invalid");

        String token = Jwts.builder()
                .setSubject("user")
                .claim("kid", "id")
                .claim("email", email)
                .signWith(SignatureAlgorithm.RS256, keyPair.getPrivate())
                .setExpiration(new Date(System.currentTimeMillis() + 300000))
                .compact();
        return token;
    }
}
