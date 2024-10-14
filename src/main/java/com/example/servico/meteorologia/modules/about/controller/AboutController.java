package com.example.servico.meteorologia.modules.about.controller;

import com.example.servico.meteorologia.modules.about.models.About;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/sobre")
public class AboutController {

    @GetMapping()
    public ResponseEntity<About> about() {
        About about = new About("Bruno Sezar M. Aiolfi", "Serviço de meteorologia");
        return ResponseEntity.ok(about);
    }
}
