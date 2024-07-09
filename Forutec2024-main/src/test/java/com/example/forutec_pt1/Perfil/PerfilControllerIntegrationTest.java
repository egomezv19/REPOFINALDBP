package com.example.forutec_pt1.Perfil;

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

@WebMvcTest(PerfilController.class)
class PerfilControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfilService perfilService;

    @MockBean
    private JwtService jwtService;

    private PerfilDTO perfilDTO;
    private String jwtToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        perfilDTO = new PerfilDTO();
        perfilDTO.setId(1L);
        perfilDTO.setInformacionAdicional("Informacion adicional");

        UserDetails userDetails = User.withUsername("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        jwtToken = jwtService.generateToken(userDetails);
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllPerfiles() throws Exception {
        when(perfilService.getAllPerfiles()).thenReturn(List.of(perfilDTO));

        mockMvc.perform(get("/api/perfiles")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].informacionAdicional").value("Informacion adicional"));

        verify(perfilService, times(1)).getAllPerfiles();
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetPerfilById() throws Exception {
        when(perfilService.getPerfilById(1L)).thenReturn(perfilDTO);

        mockMvc.perform(get("/api/perfiles/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.informacionAdicional").value("Informacion adicional"));

        verify(perfilService, times(1)).getPerfilById(1L);
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreatePerfil() throws Exception {
        when(perfilService.savePerfil(any(PerfilDTO.class))).thenReturn(perfilDTO);

        mockMvc.perform(post("/api/perfiles")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf()) // Deshabilitar CSRF para esta solicitud
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"informacionAdicional\": \"Informacion adicional\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.informacionAdicional").value("Informacion adicional"));

        verify(perfilService, times(1)).savePerfil(any(PerfilDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdatePerfil() throws Exception {
        when(perfilService.updatePerfil(anyLong(), any(PerfilDTO.class))).thenReturn(perfilDTO);

        mockMvc.perform(put("/api/perfiles/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf()) // Deshabilitar CSRF para esta solicitud
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"informacionAdicional\": \"Informacion actualizada\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.informacionAdicional").value("Informacion adicional"));

        verify(perfilService, times(1)).updatePerfil(anyLong(), any(PerfilDTO.class));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeletePerfil() throws Exception {
        doNothing().when(perfilService).deletePerfil(anyLong());

        mockMvc.perform(delete("/api/perfiles/1")
                        .header("Authorization", "Bearer " + jwtToken)
                        .with(csrf()) // Deshabilitar CSRF para esta solicitud
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(perfilService, times(1)).deletePerfil(anyLong());
    }
}
