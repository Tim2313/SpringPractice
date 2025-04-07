package com.example.horses.domain.entity;

import java.util.Optional;

public interface Identifiable<ID> {

    ID getId();

    Identifiable<ID> setId(ID id);

    static <ID> ID get(Identifiable<ID> entity) {
        return Optional.ofNullable(entity)
                .map(Identifiable::getId)
                .orElse(null);
    }

}