package com.example.forutec_pt1.Comentario;

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


@WebMvcTest(ComentarioController.class)
class ComentarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ComentarioService comentarioService;

    @MockBean
    private JwtService jwtService;

    private ComentarioDTO comentarioDTO;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        comentarioDTO = new ComentarioDTO();
        comentarioDTO.setId(1L);
        comentarioDTO.setContenido("Contenido del comentario");


        UserDetails userDetails = User.withUsername("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        jwtToken = jwtService.generateToken(userDetails);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllComentarios() throws Exception {
        when(comentarioService.getAllComentarios()).thenReturn(List.of(comentarioDTO));

        mockMvc.perform(get("/api/comentarios")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].contenido").value("Contenido del comentario"));

        verify(comentarioService, times(1)).getAllComentarios();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetComentarioById() throws Exception {
        when(comentarioService.getComentarioById(1L)).thenReturn(comentarioDTO);

        mockMvc.perform(get("/api/comentarios/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenido").value("Contenido del comentario"));

        verify(comentarioService, times(1)).getComentarioById(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateComentario() throws Exception {
        when(comentarioService.saveComentario(any(ComentarioDTO.class))).thenReturn(comentarioDTO);

        mockMvc.perform(post("/api/comentarios")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf()) // Deshabilitar CSRF para esta solicitud
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contenido\": \"Contenido del comentario\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.contenido").value("Contenido del comentario"));

        verify(comentarioService, times(1)).saveComentario(any(ComentarioDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateComentario() throws Exception {
        when(comentarioService.updateComentario(anyLong(), any(ComentarioDTO.class))).thenReturn(comentarioDTO);

        mockMvc.perform(put("/api/comentarios/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"contenido\": \"Contenido actualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contenido").value("Contenido del comentario"));

        verify(comentarioService, times(1)).updateComentario(anyLong(), any(ComentarioDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteComentario() throws Exception {
        doNothing().when(comentarioService).deleteComentario(anyLong());

        mockMvc.perform(delete("/api/comentarios/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(comentarioService, times(1)).deleteComentario(anyLong());
    }
}
