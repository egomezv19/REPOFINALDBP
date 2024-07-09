package com.example.forutec_pt1.Publicacion;

import com.example.forutec_pt1.Comentario.Comentario;
import com.example.forutec_pt1.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id", scope = Publicacion.class)
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    private ZonedDateTime fechaHoraPublicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")

    private Usuario usuario;

    @OneToMany(mappedBy = "publicacion")

    private List<Comentario> comentarios;

    @PrePersist
    protected void onCreate() {
        this.fechaHoraPublicacion = ZonedDateTime.now(ZoneId.of("America/Lima"));
    }

    public String getFormattedFechaHoraPublicacion() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss z");
        return fechaHoraPublicacion.format(formatter);
    }

}
