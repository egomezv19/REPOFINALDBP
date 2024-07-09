package com.example.forutec_pt1.Publicacion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<PublicacionDTO>> getAllPublicaciones() {
        List<PublicacionDTO> publicaciones = publicacionService.getAllPublicaciones();
        return new ResponseEntity<>(publicaciones, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> getPublicacionById(@PathVariable Long id) {
        PublicacionDTO publicacionDTO = publicacionService.getPublicacionById(id);
        return new ResponseEntity<>(publicacionDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<PublicacionDTO> savePublicacion(@RequestBody PublicacionDTO publicacionDTO) {
        PublicacionDTO savedPublicacion = publicacionService.savePublicacion(publicacionDTO);
        return new ResponseEntity<>(savedPublicacion, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> updatePublicacion(@PathVariable Long id, @RequestBody PublicacionDTO publicacionDTO) {
        PublicacionDTO updatedPublicacion = publicacionService.updatePublicacion(id, publicacionDTO);
        return new ResponseEntity<>(updatedPublicacion, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublicacion(@PathVariable Long id) {
        publicacionService.deletePublicacion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
