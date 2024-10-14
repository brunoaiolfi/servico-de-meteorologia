package com.example.servico.meteorologia.modules.users.models;

import com.example.servico.meteorologia.modules.users.enums.EnumLocale;

import java.util.UUID;

public record User(
        String name,
        EnumLocale locale,
        UUID uuid
) {
    public User toEntityUserDTO() {
        return new User(name, locale, uuid);
    }
}
