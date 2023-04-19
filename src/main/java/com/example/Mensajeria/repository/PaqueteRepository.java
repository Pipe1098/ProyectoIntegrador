package com.example.Mensajeria.repository;


import com.example.Mensajeria.model.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaqueteRepository extends JpaRepository<Paquete,Long> {
    Optional<Paquete> findById(Long id);
}
