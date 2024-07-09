package com.example.forutec_pt1.Suscripcion;

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

@WebMvcTest(SuscripcionController.class)
class SuscripcionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuscripcionService suscripcionService;

    @MockBean
    private JwtService jwtService;

    private SuscripcionDTO suscripcionDTO;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        suscripcionDTO = new SuscripcionDTO();
        suscripcionDTO.setId(1L);
        suscripcionDTO.setUsuarioId(1L);
        suscripcionDTO.setCategoriaId(1L);
        suscripcionDTO.setPublicacionId(1L);

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
    void testGetAllSuscripciones() throws Exception {
        when(suscripcionService.getAllSuscripciones()).thenReturn(List.of(suscripcionDTO));

        mockMvc.perform(get("/api/suscripciones")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usuarioId").value(1L))
                .andExpect(jsonPath("$[0].categoriaId").value(1L))
                .andExpect(jsonPath("$[0].publicacionId").value(1L));

        verify(suscripcionService, times(1)).getAllSuscripciones();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetSuscripcionById() throws Exception {
        when(suscripcionService.getSuscripcionById(1L)).thenReturn(suscripcionDTO);

        mockMvc.perform(get("/api/suscripciones/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.usuarioId").value(1L))
                .andExpect(jsonPath("$.categoriaId").value(1L))
                .andExpect(jsonPath("$.publicacionId").value(1L));

        verify(suscripcionService, times(1)).getSuscripcionById(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateSuscripcion() throws Exception {
        when(suscripcionService.saveSuscripcion(any(SuscripcionDTO.class))).thenReturn(suscripcionDTO);

        mockMvc.perform(post("/api/suscripciones")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf()) // Deshabilitar CSRF para esta solicitud
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"usuarioId\": 1, \"categoriaId\": 1, \"publicacionId\": 1}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.usuarioId").value(1L))
                .andExpect(jsonPath("$.categoriaId").value(1L))
                .andExpect(jsonPath("$.publicacionId").value(1L));

        verify(suscripcionService, times(1)).saveSuscripcion(any(SuscripcionDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteSuscripcion() throws Exception {
        doNothing().when(suscripcionService).deleteSuscripcion(anyLong());

        mockMvc.perform(delete("/api/suscripciones/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf()) // Deshabilitar CSRF para esta solicitud
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(suscripcionService, times(1)).deleteSuscripcion(anyLong());
    }
}
