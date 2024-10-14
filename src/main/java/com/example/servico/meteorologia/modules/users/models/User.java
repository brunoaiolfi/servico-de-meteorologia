package com.example.servico.meteorologia.modules.users.models;

import com.example.servico.meteorologia.modules.users.enums.EnumUserLocale;

import java.util.UUID;

public record User(
        String name,
        EnumUserLocale locale,
        UUID uuid
) {
    public User toEntityUserDTO() {
        return new User(name, locale, uuid);
    }
}
