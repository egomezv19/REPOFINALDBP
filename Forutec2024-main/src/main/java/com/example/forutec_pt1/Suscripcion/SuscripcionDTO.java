package com.example.forutec_pt1.Suscripcion;

public class SuscripcionDTO {
    private Long id;
    private Long usuarioId;
    private Long categoriaId;
    private Long publicacionId;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Long getPublicacionId() { return publicacionId; }

    public void setPublicacionId(Long publicacionId) { this.publicacionId = publicacionId; }
}
