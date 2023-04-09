package com.example.Mensajeria.repository;

import com.example.Mensajeria.model.Cliente;
import com.example.Mensajeria.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(Long id);

    Cliente getByCedula(Long cedula);
}


