package com.example.Mensajeria.repository;

import com.example.Mensajeria.model.Envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, String> {
        Optional<Envio> findById(String id);
}

