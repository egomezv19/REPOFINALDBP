package com.example.forutec_pt1.Categoria;

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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import java.util.List;
import static org.mockito.Mockito.*;

@WebMvcTest(CategoriaController.class)
class CategoriaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @MockBean
    private JwtService jwtService;

    private CategoriaDTO categoriaDTO;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(1L);
        categoriaDTO.setNombre("Categoria 1");

        // Crear un usuario simulado y generar un token JWT
        UserDetails userDetails = User.withUsername("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        jwtToken = jwtService.generateToken(userDetails);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllCategorias() throws Exception {
        when(categoriaService.getAllCategorias()).thenReturn(List.of(categoriaDTO));

        mockMvc.perform(get("/api/categorias")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Categoria 1"));

        verify(categoriaService, times(1)).getAllCategorias();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetCategoriaById() throws Exception {
        when(categoriaService.getCategoriaById(1L)).thenReturn(categoriaDTO);

        mockMvc.perform(get("/api/categorias/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Categoria 1"));

        verify(categoriaService, times(1)).getCategoriaById(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreateCategoria() throws Exception {
        when(categoriaService.saveCategoria(any(CategoriaDTO.class))).thenReturn(categoriaDTO);

        mockMvc.perform(post("/api/categorias")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Categoria 1\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Categoria 1"));

        verify(categoriaService, times(1)).saveCategoria(any(CategoriaDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdateCategoria() throws Exception {
        when(categoriaService.updateCategoria(anyLong(), any(CategoriaDTO.class))).thenReturn(categoriaDTO);

        mockMvc.perform(put("/api/categorias/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Categoria Actualizada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Categoria 1"));

        verify(categoriaService, times(1)).updateCategoria(anyLong(), any(CategoriaDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeleteCategoria() throws Exception {
        doNothing().when(categoriaService).deleteCategoria(anyLong());

        mockMvc.perform(delete("/api/categorias/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(categoriaService, times(1)).deleteCategoria(anyLong());
    }
}
