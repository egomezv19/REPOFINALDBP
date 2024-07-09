package com.example.forutec_pt1.Comentario;

import com.example.forutec_pt1.Publicacion.Publicacion;
import com.example.forutec_pt1.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = Comentario.class)
public class Comentario {
    // Getters y setters hula
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    private LocalDateTime fechaHoraComentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")

    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")

    private Publicacion publicacion;

}
