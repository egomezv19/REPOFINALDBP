package com.example.forutec_pt1.Perfil;

import com.example.forutec_pt1.Exceptions.ResourceNotFoundException;
import com.example.forutec_pt1.Usuario.Usuario;
import com.example.forutec_pt1.Usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<PerfilDTO> getAllPerfiles() {
        return perfilRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PerfilDTO getPerfilById(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id: " + id));
        return convertToDTO(perfil);
    }

    public PerfilDTO savePerfil(PerfilDTO perfilDTO) {
        if (perfilRepository.existsByUsuarioId(perfilDTO.getUsuarioId())) {
            throw new RuntimeException("El usuario ya tiene un perfil asociado.");
        }
        Perfil perfil = new Perfil();
        perfil.setUsuario(usuarioRepository.findById(perfilDTO.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + perfilDTO.getUsuarioId())));
        perfil.setInformacionAdicional(perfilDTO.getInformacionAdicional());
        perfil = perfilRepository.save(perfil);
        return convertToDTO(perfil);
    }
    public PerfilDTO updatePerfil(Long id, PerfilDTO perfilDTO) {
        Perfil perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id: " + id));

        perfilExistente.setInformacionAdicional(perfilDTO.getInformacionAdicional());
        perfilExistente.setUsuario(new Usuario());
        perfilExistente.getUsuario().setId(perfilDTO.getUsuarioId());

        Perfil updatedPerfil = perfilRepository.save(perfilExistente);
        return convertToDTO(updatedPerfil);
    }
    public PerfilDTO patchPerfil(Long id, PerfilDTO perfilDTO) {
        Perfil perfilExistente = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id: " + id));

        if (perfilDTO.getInformacionAdicional() != null) {
            perfilExistente.setInformacionAdicional(perfilDTO.getInformacionAdicional());
        }
        if (perfilDTO.getUsuarioId() != null) {
            perfilExistente.setUsuario(new Usuario());
            perfilExistente.getUsuario().setId(perfilDTO.getUsuarioId());
        }

        Perfil patchedPerfil = perfilRepository.save(perfilExistente);
        return convertToDTO(patchedPerfil);
    }

    public void deletePerfil(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Perfil no encontrado con id: " + id));
        perfilRepository.delete(perfil);
    }

    private PerfilDTO convertToDTO(Perfil perfil) {
        PerfilDTO dto = new PerfilDTO();
        dto.setId(perfil.getId());
        dto.setUsuarioId(perfil.getUsuario().getId());
        dto.setInformacionAdicional(perfil.getInformacionAdicional());
        return dto;
    }

    private Perfil convertToEntity(PerfilDTO perfilDTO) {
        Perfil perfil = new Perfil();
        perfil.setId(perfilDTO.getId());
        perfil.setUsuario(new Usuario());
        perfil.getUsuario().setId(perfilDTO.getUsuarioId());
        perfil.setInformacionAdicional(perfilDTO.getInformacionAdicional());
        return perfil;
    }
}
