package com.example.forutec_pt1.Perfil;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    boolean existsByUsuarioId(Long usuarioId);
}
