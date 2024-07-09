package com.example.forutec_pt1.Suscripcion;

import com.example.forutec_pt1.Categoria.Categoria;
import com.example.forutec_pt1.Categoria.CategoriaRepository;
import com.example.forutec_pt1.Publicacion.Publicacion;
import com.example.forutec_pt1.Publicacion.PublicacionRepository;
import com.example.forutec_pt1.Exceptions.ResourceNotFoundException;
import com.example.forutec_pt1.Usuario.Usuario;
import com.example.forutec_pt1.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SuscripcionService {

    @Autowired
    private SuscripcionRepository suscripcionRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PublicacionRepository publicacionRepository;


    public List<SuscripcionDTO> getAllSuscripciones() {
        return suscripcionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SuscripcionDTO getSuscripcionById(Long id) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripcion no encontrada con id: " + id));
        return convertToDTO(suscripcion);
    }

    public SuscripcionDTO saveSuscripcion(SuscripcionDTO suscripcionDTO) {
        Usuario usuario = usuarioRepository.findById(suscripcionDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + suscripcionDTO.getUsuarioId()));
        Categoria categoria = categoriaRepository.findById(suscripcionDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada con id: " + suscripcionDTO.getCategoriaId()));
        Publicacion publicacion = publicacionRepository.findById(suscripcionDTO.getPublicacionId())
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion no encontrada con id: " + suscripcionDTO.getPublicacionId()));

        Suscripcion suscripcion = convertToEntity(suscripcionDTO);
        suscripcion.setUsuario(usuario);
        suscripcion.setCategoria(categoria);
        suscripcion.setPublicacion(publicacion);

        Suscripcion savedSuscripcion = suscripcionRepository.save(suscripcion);
        return convertToDTO(savedSuscripcion);
    }
    public SuscripcionDTO createSuscripcion(SuscripcionDTO suscripcionDTO) {
        Usuario usuario = usuarioRepository.findById(suscripcionDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        Categoria categoria = categoriaRepository.findById(suscripcionDTO.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));

        Publicacion publicacion = publicacionRepository.findById(suscripcionDTO.getPublicacionId())
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion no encontrada"));

        Suscripcion suscripcion = convertToEntity(suscripcionDTO);
        suscripcion.setUsuario(usuario);
        suscripcion.setCategoria(categoria);
        suscripcion.setPublicacion(publicacion);

        Suscripcion savedSuscripcion = suscripcionRepository.save(suscripcion);
        return convertToDTO(savedSuscripcion);
    }

    public void deleteSuscripcion(Long id) {
        Suscripcion suscripcion = suscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suscripcion no encontrada con id: " + id));
        suscripcionRepository.delete(suscripcion);
    }

    private SuscripcionDTO convertToDTO(Suscripcion suscripcion) {
        SuscripcionDTO suscripcionDTO = new SuscripcionDTO();
        suscripcionDTO.setId(suscripcion.getId());
        suscripcionDTO.setUsuarioId(suscripcion.getUsuario().getId());
        suscripcionDTO.setCategoriaId(suscripcion.getCategoria().getId());
        suscripcionDTO.setPublicacionId(suscripcion.getPublicacion().getId());
        return suscripcionDTO;
    }

    private Suscripcion convertToEntity(SuscripcionDTO suscripcionDTO) {
        Suscripcion suscripcion = new Suscripcion();
        suscripcion.setId(suscripcionDTO.getId());
        suscripcion.setUsuario(new Usuario());
        suscripcion.getUsuario().setId(suscripcionDTO.getUsuarioId());
        suscripcion.setCategoria(new Categoria());
        suscripcion.getCategoria().setId(suscripcionDTO.getCategoriaId());
        suscripcion.setPublicacion(new Publicacion());
        suscripcion.getPublicacion().setId(suscripcionDTO.getPublicacionId());
        return suscripcion;
    }
}
