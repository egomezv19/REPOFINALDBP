package com.example.forutec_pt1.Usuario;

import com.example.forutec_pt1.Exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    ModelMapper modelMapper;

    public UserDetailsService userDetailsService() {
        return username -> usuarioRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public List<UsuarioDTO> getAllUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public Usuario obtenerPorId2(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }

    public UsuarioDTO getUsuarioById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        return convertToDTO(usuario);
    }

    public UsuarioDTO saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = convertToEntity(usuarioDTO);
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return convertToDTO(savedUsuario);
    }
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public UsuarioDTO updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        usuarioExistente.setFirstname(usuarioDTO.getFirstname());
        usuarioExistente.setLastname(usuarioDTO.getLastname());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        Usuario updatedUsuario = usuarioRepository.save(usuarioExistente);
        return convertToDTO(updatedUsuario);
    }

    public void deleteUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));
        usuarioRepository.delete(usuario);
    }
    public UsuarioDTO patchUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id: " + id));

        if (usuarioDTO.getFirstname() != null) {
            usuarioExistente.setFirstname(usuarioDTO.getFirstname());
        }
        if (usuarioDTO.getLastname() != null) {
            usuarioExistente.setLastname(usuarioDTO.getLastname());
        }
        if (usuarioDTO.getEmail() != null) {
            usuarioExistente.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getPassword() != null) {
            usuarioExistente.setContrasena(usuarioDTO.getPassword());
        }


        Usuario patchedUsuario = usuarioRepository.save(usuarioExistente);
        return convertToDTO(patchedUsuario);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setFirstname(usuario.getFirstname());
        usuarioDTO.setLastname(usuario.getLastname());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }

    private Usuario convertToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setFirstname(usuarioDTO.getFirstname());
        usuario.setLastname(usuarioDTO.getLastname());
        usuario.setEmail(usuarioDTO.getEmail());
        return usuario;
    }
}
