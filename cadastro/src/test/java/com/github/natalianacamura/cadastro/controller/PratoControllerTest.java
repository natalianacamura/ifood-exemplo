package com.github.natalianacamura.cadastro.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.natalianacamura.cadastro.model.Prato;
import com.github.natalianacamura.cadastro.service.PratoService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class PratoControllerTest {

    @Mock
    private PratoService pratoServiceMock;

    @Test
    void dadoGetNoControllerEntaoGetPratosDeveTerSidoChamado() {
        PratoController controller = new PratoController(pratoServiceMock);

        controller.get();

        Mockito.verify(pratoServiceMock).getPratos();
    }

    @Test
    void dadoPostNoControllerEntaoInsereDeveTerSidoChamado() {
        PratoController controller = new PratoController(pratoServiceMock);

        Prato prato = new Prato();

        controller.post(prato);

        Mockito.verify(pratoServiceMock).insere(prato);
    }

    @Test
    void dadoPutNoControllerEntaoUpdateDeveTerSidoChamado() throws NotFoundException {
        PratoController controller = new PratoController(pratoServiceMock);

        Prato prato = new Prato();

        controller.put(1L, prato);

        Mockito.verify(pratoServiceMock).update(1L, prato);
    }

    @Test
    void dadoDeleteNoControllerEntaoDeleteDeveTerSidoChamado() throws NotFoundException {
        PratoController controller = new PratoController(pratoServiceMock);

        controller.delete(1L);

        Mockito.verify(pratoServiceMock).delete(1L);
    }

    @Test
    void dadoPutNoControllerQuandoNaoEncontradoPratoEntaoRespostaDeveSer404()  throws NotFoundException {
        PratoController controller = new PratoController(pratoServiceMock);

        Mockito.doThrow(new NotFoundException("Prato não encontrado!")).when(pratoServiceMock).update(Mockito.anyLong(), Mockito.any());

        ResponseEntity result = controller.put(1L, Mockito.mock(Prato.class));

        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void dadoDeleteNoControllerQuandoNaoEncontradoPratoEntaoRespostaDeveSer404()
        throws NotFoundException {
        PratoController controller = new PratoController(pratoServiceMock);

        Mockito.doThrow(new NotFoundException("Prato não encontrado!")).when(pratoServiceMock).delete(1L);

        ResponseEntity result = controller.delete(1L);

        assertEquals(404, result.getStatusCodeValue());
    }
}