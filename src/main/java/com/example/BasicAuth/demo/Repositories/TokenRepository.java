package com.example.BasicAuth.demo.Repositories;

import com.example.BasicAuth.demo.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface TokenRepository extends JpaRepository<Token, Long> {

    //        expireDate                    value                                 isDel
    // '1', '2024-04-25 21:58:17.639000', '65c338ac-69f6-43a4-b460-dd4dd3e39534', 0

//    Optional<Token> findByvalueAndisDeletedEquals(String token, boolean isDeleted);
//
//     /*
//          To validate token
//          1. Check if token value is present
//          2. Check if token is not deleted
//          3. Check if token is not expired
//         */
//
//    Optional<Token> findByvalueAndisDeletedEqualsAndvalidUptoGreaterThan(
//            String token, boolean isDeleted, Date date);

    Optional<Token> findByValueAndIsDeleted(String value, boolean isDeleted);

    Optional<Token> findByValueAndIsDeletedAndValidUptoGreaterThan(String value, boolean isDeleted, Date validUpto);


}
