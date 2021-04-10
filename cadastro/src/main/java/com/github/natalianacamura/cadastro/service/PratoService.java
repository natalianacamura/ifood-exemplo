package com.github.natalianacamura.cadastro.service;

import com.github.natalianacamura.cadastro.model.Prato;
import com.github.natalianacamura.cadastro.repository.PratoRepository;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PratoService {

    private final PratoRepository repository;

    @Autowired
    public PratoService(PratoRepository repository) {
        this.repository = repository;
    }

    public List<Prato> getPratos() {
        return repository.findAll();
    }

    public Long insere(Prato prato) {
        return repository.save(prato).getId();
    }

    public void update(Long id, Prato dto) throws NotFoundException {
        Optional<Prato> pratoOpt = repository.findById(id);
        if (!pratoOpt.isPresent()) {
            throw new NotFoundException("Prato nao encontrado");
        }
        Prato prato = pratoOpt.get();
        prato.setPreco(dto.getPreco());

        repository.save(prato);
    }

    public void delete(Long id) throws NotFoundException {
        Optional<Prato> prato = repository.findById(id);
        if (!prato.isPresent()) {
            throw new NotFoundException("Prato nao encontrado");
        }

        repository.delete(prato.get());
    }

}
