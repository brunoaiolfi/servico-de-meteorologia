package com.example.servico.meteorologia.modules.users.controllers;

import com.example.servico.meteorologia.modules.users.enums.EnumUserLocale;
import com.example.servico.meteorologia.modules.users.models.DTO.UserDTO;
import com.example.servico.meteorologia.modules.users.models.User;
import com.example.servico.meteorologia.modules.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Object> list(@RequestParam(required = false) UUID uuid) {
        try {
            return userService.list(uuid);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<Object> save(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.name().isBlank() || userDTO.locale().isBlank()) {
                return ResponseEntity.badRequest().body("Erro: Preencha os campos corretamente!");
            }

            UUID uuid = UUID.randomUUID();
            EnumUserLocale locale = EnumUserLocale.getByName(userDTO.locale());
            User user = new User(userDTO.name(), locale, uuid);

            return userService.save(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        try {
            if (id.toString().isBlank() || userDTO.name().isBlank() || userDTO.locale().isBlank()) {
                return ResponseEntity.badRequest().body("Erro: Preencha os campos corretamente!");
            }

            EnumUserLocale locale = EnumUserLocale.getByName(userDTO.locale());
            User user = new User(userDTO.name(), locale, id);

            return userService.update(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        try {
            if (id.toString().isBlank()) {
                return ResponseEntity.badRequest().body("Erro: Preencha os campos corretamente!");
            }

            return userService.delete(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }

}
