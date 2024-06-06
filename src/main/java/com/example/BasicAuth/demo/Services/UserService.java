package com.example.BasicAuth.demo.Services;

import com.example.BasicAuth.demo.DTOs.SignUpRequestDTO;
import com.example.BasicAuth.demo.Repositories.TokenRepository;
import com.example.BasicAuth.demo.Repositories.UserRepository;
import com.example.BasicAuth.demo.models.Token;
import com.example.BasicAuth.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private SignUpRequestDTO signUpRequestDTO ;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

    @Autowired
    private TokenRepository tokenRepository;


    public User signUp(String name,String email, String password) {
        User user1 = new User() ;
        user1.setName(name);
        user1.setEmail(email);
        user1.setPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user1) ;

//        return new ResponseEntity<>(user1, HttpStatus.OK) ;
    }

    public Token login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email) ;
        if(!userOptional.isPresent()){
            throw new RuntimeException("Invalid userOptional or password");
        }

        User user = userOptional.get() ;

        if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("Invalid userOptional or password");
        }

        Token token = new Token();
        token.setUser(user);
        token.setValue(UUID.randomUUID().toString());

        Date expiredDate = getexpiryDate();

        token.setValidUpto(expiredDate);

        return tokenRepository.save(token);
    }

    private Token generateToken(User user) {

        Token token = new Token() ;
        token.setUser(user);
        token.setValue(UUID.randomUUID().toString());
        Date expiryDate = getexpiryDate() ;
        token.setValidUpto(expiryDate);
        return token ;
    }

    private Date getexpiryDate() {
        Calendar calenderDate = Calendar.getInstance() ;
        calenderDate.setTime(new Date());
        calenderDate.add(Calendar.DAY_OF_MONTH,5) ;
        Date expiryDate = calenderDate.getTime() ;
        return expiryDate ;
    }

    public void logout(String token) {
        // '1', '2024-04-25 21:58:17.639000', '65c338ac-69f6-43a4-b460-dd4dd3e39534', '1'

        Optional<Token> tokenOptional = tokenRepository.findByValueAndIsDeleted(token, false);

        if (tokenOptional.isEmpty()) {
            throw new RuntimeException("Token is invalid ");
        }

        Token tokenObject = tokenOptional.get();
        tokenObject.setDeleted(true);

        tokenRepository.save(tokenObject);
    }

    public boolean validateToken(String token) {

        /*
          To validate token
          1. Check if token value is present
          2. Check if token is not deleted
          3. Check if token is not expired
         */

        Optional<Token> tokenOptional = tokenRepository.findByValueAndIsDeletedAndValidUptoGreaterThan(
                token, false, new Date()
        );

        if (tokenOptional.isEmpty()) {
            return false;
        }
        return true;
    }


}

