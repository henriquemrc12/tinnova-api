package com.tinnova.api.veiculo.controller;

import com.tinnova.api.veiculo.forms.VeiculoCreateForm;
import com.tinnova.api.veiculo.forms.VeiculoUpdateForm;
import com.tinnova.api.veiculo.forms.VendidoUpdateForm;
import com.tinnova.api.veiculo.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VeiculoCreateForm form) {
        service.create(form);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody VeiculoUpdateForm form) {
        service.update(form, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PatchMapping(value="/{id}/{vendido}")
    public ResponseEntity<?> updateVendido(@PathVariable Long id, @PathVariable Boolean vendido) {
        service.updateVendido(vendido, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
