package com.example.forutec_pt1.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.getUsuarioById(id);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<UsuarioDTO> saveUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO savedUsuario = usuarioService.saveUsuario(usuarioDTO);
        return ResponseEntity.status(201).body(savedUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/profile/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId2(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId2(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

}
