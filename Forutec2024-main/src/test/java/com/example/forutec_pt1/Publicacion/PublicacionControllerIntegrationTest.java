package com.example.forutec_pt1.Publicacion;

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

@WebMvcTest(PublicacionController.class)
class PublicacionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PublicacionService publicacionService;

    @MockBean
    private JwtService jwtService;

    private PublicacionDTO publicacionDTO;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        publicacionDTO = new PublicacionDTO();
        publicacionDTO.setId(1L);
        publicacionDTO.setContenido("Contenido de la publicacion");


        UserDetails userDetails = User.withUsername("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        jwtToken = jwtService.generateToken(userDetails);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllPublicaciones() throws Exception {
        when(publicacionService.getAllPublicaciones()).thenReturn(List.of(publicacionDTO));

        mockMvc.perform(get("/api/publicaciones")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contenido").value("Contenido de la publicacion"));

        verify(publicacionService, times(1)).getAllPublicaciones();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetPublicacionById() throws Exception {
        when(publicacionService.getPublicacionById(1L)).thenReturn(publicacionDTO);

        mockMvc.perform(get("/api/publicaciones/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenido").value("Contenido de la publicacion"));

        verify(publicacionService, times(1)).getPublicacionById(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreatePublicacion() throws Exception {
        when(publicacionService.savePublicacion(any(PublicacionDTO.class))).thenReturn(publicacionDTO);

        mockMvc.perform(post("/api/publicaciones")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contenido\": \"Contenido de la publicacion\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contenido").value("Contenido de la publicacion"));

        verify(publicacionService, times(1)).savePublicacion(any(PublicacionDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdatePublicacion() throws Exception {
        when(publicacionService.updatePublicacion(anyLong(), any(PublicacionDTO.class))).thenReturn(publicacionDTO);

        mockMvc.perform(put("/api/publicaciones/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contenido\": \"Contenido actualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenido").value("Contenido de la publicacion"));

        verify(publicacionService, times(1)).updatePublicacion(anyLong(), any(PublicacionDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeletePublicacion() throws Exception {
        doNothing().when(publicacionService).deletePublicacion(anyLong());

        mockMvc.perform(delete("/api/publicaciones/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(publicacionService, times(1)).deletePublicacion(anyLong());
    }
}
