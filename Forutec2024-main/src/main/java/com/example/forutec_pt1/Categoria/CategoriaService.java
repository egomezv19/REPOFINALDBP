package com.example.forutec_pt1.Categoria;

import com.example.forutec_pt1.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Obtener todas las categorías
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una categoría por ID
    public CategoriaDTO getCategoriaById(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id: " + id));
        return convertToDTO(categoria);
    }

    // Guardar una nueva categoría
    public CategoriaDTO saveCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = convertToEntity(categoriaDTO);
        Categoria savedCategoria = categoriaRepository.save(categoria);
        return convertToDTO(savedCategoria);
    }

    // Actualizar una categoría existente
    public CategoriaDTO updateCategoria(Long id, CategoriaDTO categoriaDTO) {
        Categoria categoriaExistente = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id: " + id));

        categoriaExistente.setNombre(categoriaDTO.getNombre());
        Categoria updatedCategoria = categoriaRepository.save(categoriaExistente);
        return convertToDTO(updatedCategoria);
    }

    // Eliminar una categoría por ID
    public void deleteCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id: " + id));
        categoriaRepository.delete(categoria);
    }

    // Convertir entidad a DTO
    private CategoriaDTO convertToDTO(Categoria categoria) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());
        return categoriaDTO;
    }

    // Convertir DTO a entidad
    private Categoria convertToEntity(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNombre(categoriaDTO.getNombre());
        return categoria;
    }
}
