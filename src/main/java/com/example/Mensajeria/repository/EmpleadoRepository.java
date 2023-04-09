package com.example.Mensajeria.repository;

import com.example.Mensajeria.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    Optional<Empleado> findById(Long id);

    Empleado findByCedula(Long cedula);
}