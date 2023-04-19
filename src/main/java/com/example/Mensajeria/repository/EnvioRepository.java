package com.example.Mensajeria.repository;

import com.example.Mensajeria.model.Empleado;
import com.example.Mensajeria.model.Envio;
import com.example.Mensajeria.service.EnvioService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, String> {
        Optional<Envio> findById(String id);

    List<Envio> findByEstadoEnvio(EnvioService.EstadoEnvio estado);
}

