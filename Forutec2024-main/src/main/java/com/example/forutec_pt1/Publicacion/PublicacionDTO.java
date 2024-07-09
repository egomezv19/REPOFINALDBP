package com.example.forutec_pt1.Publicacion;

public class PublicacionDTO {
    private Long id;
    private String contenido;
    private String fechaHoraPublicacion;
    private Long usuarioId;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFechaHoraPublicacion() {
        return fechaHoraPublicacion;
    }

    public void setFechaHoraPublicacion(String fechaHoraPublicacion) {
        this.fechaHoraPublicacion = fechaHoraPublicacion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
