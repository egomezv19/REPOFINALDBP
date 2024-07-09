package com.example.forutec_pt1.Suscripcion;

import com.example.forutec_pt1.Publicacion.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    List<Suscripcion> findByPublicacion(Publicacion publicacion);
}


