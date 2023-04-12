package com.example.Mensajeria.repository;

import com.example.Mensajeria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findById(Long id);

    Optional<Cliente> getByCedula(Long cedula);

    @Override
    void deleteById(Long aLong);

    Optional<Cliente> findByCedula(Long cedula);

    void deleteByCedula(Long cedula);
}


