package com.example.forutec_pt1.Categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> getAllCategorias() {
        List<CategoriaDTO> categorias = categoriaService.getAllCategorias();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> getCategoriaById(@PathVariable Long id) {
        CategoriaDTO categoriaDTO = categoriaService.getCategoriaById(id);
        return new ResponseEntity<>(categoriaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PostMapping
    public ResponseEntity<CategoriaDTO> saveCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO savedCategoria = categoriaService.saveCategoria(categoriaDTO);
        return new ResponseEntity<>(savedCategoria, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')or hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> updateCategoria(@PathVariable Long id, @RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO updatedCategoria = categoriaService.updateCategoria(id, categoriaDTO);
        return new ResponseEntity<>(updatedCategoria, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
