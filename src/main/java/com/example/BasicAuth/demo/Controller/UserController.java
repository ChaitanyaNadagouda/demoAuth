package com.example.BasicAuth.demo.Controller;

import com.example.BasicAuth.demo.DTOs.LoginRequestDTO;
import com.example.BasicAuth.demo.DTOs.SignUpRequestDTO;
import com.example.BasicAuth.demo.Services.UserService;
import com.example.BasicAuth.demo.models.Token;
import com.example.BasicAuth.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/hello")
    public String sayHello(){
//        System.out.println("helooooooo");
        return "HelloWorld" ;
    }

    @Autowired
    private SignUpRequestDTO signUpRequestDTO ;

    @Autowired
    private LoginRequestDTO loginRequestDTO ;

    @Autowired
    private UserService userService ;

    @PostMapping("/signUp")
    public User signUp(@RequestBody SignUpRequestDTO signUpRequestDTO){
        return userService.signUp(signUpRequestDTO.getName(),signUpRequestDTO.getEmail(),signUpRequestDTO.getPassword()) ;
    }


    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDTO loginRequestDTO){
        return userService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword()) ;
    }


    @PostMapping("/logout/{token}")
    public ResponseEntity<Void> logout(@PathVariable("token") String token){
        userService.logout(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("validateToken/{token}")
    public boolean validate(@PathVariable("token") String token){
        return userService.validateToken(token);
    }



}
