package com.example.forutec_pt1.Usuario;

import com.example.forutec_pt1.config.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private JwtService jwtService;

    private UsuarioDTO usuarioDTO;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setFirstname("John");
        usuarioDTO.setLastname("Doe");
        usuarioDTO.setEmail("john.doe@example.com");
        usuarioDTO.setPassword("password");

        // Crear un usuario simulado y generar un token JWT
        UserDetails userDetails = User.withUsername("admin")
                .password("password")
                .roles("ADMIN")
                .build();
        // Simular la generación del token JWT
        jwtToken = jwtService.generateToken(userDetails);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllUsuarios() throws Exception {
        when(usuarioService.getAllUsuarios()).thenReturn(List.of(usuarioDTO));

        mockMvc.perform(get("/api/usuarios")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstname").value("John"))
                .andExpect(jsonPath("$[0].lastname").value("Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"));

        verify(usuarioService, times(1)).getAllUsuarios();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetUsuarioById() throws Exception {
        when(usuarioService.getUsuarioById(1L)).thenReturn(usuarioDTO);

        mockMvc.perform(get("/api/usuarios/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(usuarioService, times(1)).getUsuarioById(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateUsuario() throws Exception {
        when(usuarioService.saveUsuario(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(post("/api/usuarios")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf()) // Deshabilitar CSRF para esta solicitud
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstname\": \"John\", \"lastname\": \"Doe\", \"email\": \"john.doe@example.com\", \"password\": \"password\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname").value("John"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));

        verify(usuarioService, times(1)).saveUsuario(any(UsuarioDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteUsuario() throws Exception {
        doNothing().when(usuarioService).deleteUsuario(anyLong());

        mockMvc.perform(delete("/api/usuarios/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf()) // Deshabilitar CSRF para esta solicitud
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(usuarioService, times(1)).deleteUsuario(anyLong());
    }
}
