package com.example.forutec_pt1.Comentario;

public class ComentarioDTO {
    private Long id;
    private String contenido;
    private String fechaHoraComentario;
    private Long usuarioId;
    private Long publicacionId;

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

    public String getFechaHoraComentario() {
        return fechaHoraComentario;
    }

    public void setFechaHoraComentario(String fechaHoraComentario) {
        this.fechaHoraComentario = fechaHoraComentario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(Long publicacionId) {
        this.publicacionId = publicacionId;
    }
}
