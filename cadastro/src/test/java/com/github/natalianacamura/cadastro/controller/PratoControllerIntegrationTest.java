package com.github.natalianacamura.cadastro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.natalianacamura.cadastro.configuration.AbstractIntegrationTest;
import com.github.natalianacamura.cadastro.model.Prato;
import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@SpringBootTest
class PratoControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private PratoController controller;

    @Test
    void getTest() {
        ResponseEntity<List<Prato>> result = controller.get();

        assertEquals(0, result.getBody().size());
    }

    @Test
    @Transactional
    public void dadoInsercaoEntaoDeveTerResposta201() {
        Prato prato = new Prato();
        prato.setDescricao("Pizza");
        prato.setNome("PIzza");
        prato.setPreco(new BigDecimal(10));

        ResponseEntity<Long> result = controller.post(prato);
        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    @Transactional
    public void dadoInsercaoEntaoDeveTerURICorreta() {
        Prato prato = new Prato();
        prato.setDescricao("Pizza");
        prato.setNome("PIzza");
        prato.setPreco(new BigDecimal(10));

        ResponseEntity<Long> result = controller.post(prato);

        URI expectedURI = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(1)
            .toUri();

        assertEquals(expectedURI, result.getHeaders().getLocation());
    }
}