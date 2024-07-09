package com.example.forutec_pt1.Suscripcion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suscripciones")
public class SuscripcionController {

    @Autowired
    private SuscripcionService suscripcionService;

    @GetMapping
    public ResponseEntity<List<SuscripcionDTO>> getAllSuscripciones() {
        List<SuscripcionDTO> suscripciones = suscripcionService.getAllSuscripciones();
        return new ResponseEntity<>(suscripciones, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuscripcionDTO> getSuscripcionById(@PathVariable Long id) {
        SuscripcionDTO suscripcionDTO = suscripcionService.getSuscripcionById(id);
        return new ResponseEntity<>(suscripcionDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuscripcionDTO> createSuscripcion(@RequestBody SuscripcionDTO suscripcionDTO) {
        SuscripcionDTO savedSuscripcion = suscripcionService.saveSuscripcion(suscripcionDTO);
        return new ResponseEntity<>(savedSuscripcion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuscripcion(@PathVariable Long id) {
        suscripcionService.deleteSuscripcion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
