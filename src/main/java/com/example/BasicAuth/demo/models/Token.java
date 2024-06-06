package com.example.BasicAuth.demo.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
//@AllArgsConstructor
public class Token extends BaseModel{

    @OneToOne
    private User user ;
    private Date validUpto ;
//    private Date expireAt;
    private boolean isDeleted ;
    private String value ;

    public Token() {

    }

//    public Token(Date validUpto, boolean isDeleted) {
//        this.validUpto = validUpto;
//        this.isDeleted = isDeleted;
//    }
}
