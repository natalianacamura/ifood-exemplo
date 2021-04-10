package com.github.natalianacamura.cadastro.controller;

import com.github.natalianacamura.cadastro.model.Prato;
import com.github.natalianacamura.cadastro.service.PratoService;
import java.net.URI;
import java.util.List;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
@RequestMapping("cadastro")
public class PratoController {

    private final PratoService service;

    @Autowired
    public PratoController(PratoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Prato>> get() {
        return ResponseEntity.ok(service.getPratos());
    }

    @PostMapping
    public ResponseEntity<Long> post(@RequestBody Prato prato) {
        Long id = service.insere(prato);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathVariable Long id, @RequestBody Prato prato) {
        try {
            service.update(id, prato);

            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            service.delete(id);

            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
