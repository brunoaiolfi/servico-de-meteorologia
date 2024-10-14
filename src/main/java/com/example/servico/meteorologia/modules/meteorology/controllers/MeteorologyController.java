package com.example.servico.meteorologia.modules.meteorology.controllers;

import com.example.servico.meteorologia.modules.meteorology.models.Locale;
import com.example.servico.meteorologia.modules.meteorology.services.MeteorologyService;
import com.example.servico.meteorologia.modules.users.models.User;
import com.example.servico.meteorologia.modules.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/meteorology")
public class MeteorologyController {

    @Autowired
    private UserService userService;

    @Autowired
    private MeteorologyService meteorologyService;

    @GetMapping()
    public ResponseEntity<String> getTemperatureMessage(@RequestParam() UUID userId) {
        try {
            if (userId.equals(null)) {
                return ResponseEntity.badRequest().body("Erro: Preencha os campos corretamente!");
            }

            List<User> users = (List<User>) userService.list(userId).getBody();
            Optional<User> userSelected = users.stream().filter(u -> u.uuid().equals(userId)).findFirst();

            if (userSelected.isEmpty()) {
                return ResponseEntity.badRequest().body("Erro: Usuário não encontrado!");
            }

            Locale locale = Locale.userLocaleToLocale(userSelected.get().locale());
            ResponseEntity<Float> temperature = meteorologyService.getTemperature(locale);

            String response;

            if (temperature.getBody() < 15) {
                response = "Olá, " + userSelected.get().name() + "! A temperatura atual esta de " + temperature.getBody().toString() + "°C. Considere levar roupas quentes com você!";
            } else if (temperature.getBody() >= 15 && temperature.getBody() < 25) {
                response = "Olá, " + userSelected.get().name() + "! A temperatura atual esta de " + temperature.getBody().toString() + "°C. O tempo está fresco, uma jaqueta leve pode ser útil!";
            } else {
                response = "Olá, " + userSelected.get().name() + "! A temperatura atual esta de " + temperature.getBody().toString() + "°C. O tempo está bem quente. Quem sabe uma prainha?";
            }

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }
}
