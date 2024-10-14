package com.example.servico.meteorologia.modules.users.services;

import com.example.servico.meteorologia.modules.users.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private static final String FILE_PATH = "users.json";
    private ObjectMapper objectMapper = new ObjectMapper(); // classe da lib Jackson.

    public ResponseEntity<Object> save(User user) {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<List<User>>() {
                });
            }

            users.add(user);

            objectMapper.writeValue(new File(FILE_PATH), users);
            return ResponseEntity.ok(user);
        } catch (IOException e) {
            throw new IllegalArgumentException("Ocorreu um erro ao salvar usuário! " + e.getMessage());
        }
    }

    public ResponseEntity<Object> update(User user) {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<List<User>>() {
                });
            }

            for (int i = 0; i < users.size(); i++) {
                User existingUser = users.get(i);
                if (existingUser.uuid().equals(user.uuid())) {
                    users.set(i, user);
                    break;
                }
            }

            objectMapper.writeValue(new File(FILE_PATH), users);
            return ResponseEntity.ok(user);
        } catch (IOException e) {
            throw new IllegalArgumentException("Ocorreu um erro ao editar usuário! " + e.getMessage());
        }
    }

    public ResponseEntity<Object> delete(UUID uuid) {
        List<User> users = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<List<User>>() {
                });
            }

            for (int i = 0; i < users.size(); i++) {
                User existingUser = users.get(i);
                if (existingUser.uuid().equals(uuid)) {
                    users.remove(i);
                    break;
                }
            }

            objectMapper.writeValue(new File(FILE_PATH), users);
            return ResponseEntity.ok("Removido com sucesso!");
        } catch (IOException e) {
            throw new IllegalArgumentException("Ocorreu um erro ao deletar usuário! " + e.getMessage());
        }
    }

    public ResponseEntity<Object> list(UUID uuid) {
        List<User> users = new ArrayList<>();
        List<User> usersSelected = new ArrayList<>();

        File file = new File(FILE_PATH);

        try {
            if (file.exists()) {
                users = objectMapper.readValue(file, new TypeReference<List<User>>() {
                });
            }

            if (uuid != null) {
                for (User user : users) {
                    if (user.uuid().equals(uuid)) {
                        usersSelected.add(user);
                    }
                }

                return ResponseEntity.ok(usersSelected);
            }

            return ResponseEntity.ok(users);
        } catch (IOException e) {
            throw new IllegalArgumentException("Ocorreu um erro ao listar usuários! " + e.getMessage());
        }
    }
}
