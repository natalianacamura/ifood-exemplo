package com.ifoodexemplo.cadastro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cadastro")
public class CadastroController {

	@RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> jogar() {
        return ResponseEntity.ok("olair");
    }
}
