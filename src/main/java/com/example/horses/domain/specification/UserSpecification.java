package com.example.horses.domain.specification;

import com.example.horses.domain.entity.User;
import com.example.horses.domain.entity.User_;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> byEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(User_.EMAIL), email);
    }

}
