package com.example.BasicAuth.demo.DTOs;

import com.example.BasicAuth.demo.models.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class LoginRequestDTO extends BaseModel {

    private String email ;
    private String password ;

}

