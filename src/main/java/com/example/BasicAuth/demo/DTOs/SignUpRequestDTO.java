package com.example.BasicAuth.demo.DTOs;

import com.example.BasicAuth.demo.models.BaseModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class SignUpRequestDTO extends BaseModel {

    private String name ;
    private String email ;
    private String password ;

}
