package com.example.servico.meteorologia.modules.users.models.DTO;

public record UserDTO(
        String name,
        String locale
) {
    public UserDTO toEntityUserDTO() {
        return new UserDTO(name, locale);
    }
}
