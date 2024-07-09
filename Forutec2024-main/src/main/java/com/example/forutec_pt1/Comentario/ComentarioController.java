package com.example.forutec_pt1.Comentario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<ComentarioDTO>> getAllComentarios() {
        List<ComentarioDTO> comentarios = comentarioService.getAllComentarios();
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioDTO> getComentario(@PathVariable Long id) {
        ComentarioDTO comentario = comentarioService.getComentarioById(id);
        return ResponseEntity.ok(comentario);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<ComentarioDTO> saveComentario(@RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO savedComentario = comentarioService.saveComentario(comentarioDTO);
        return ResponseEntity.status(201).body(savedComentario);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioDTO> updateComentario(@PathVariable Long id, @RequestBody ComentarioDTO comentarioDTO) {
        ComentarioDTO updatedComentario = comentarioService.updateComentario(id, comentarioDTO);
        return new ResponseEntity<>(updatedComentario, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteComentario(id);
        return ResponseEntity.noContent().build();
    }

}

