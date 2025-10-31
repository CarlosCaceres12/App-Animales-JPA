package com.c3.animalesjpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.c3.animalesjpa.entities.Gato;

import jakarta.transaction.Transactional;

import java.util.List;


public interface GatoRepository extends JpaRepository<Gato, Long> {
    List<Gato> findByNombre(String nombre);

    /*
     * COALESCE(:campo, obj.campo) si el campo es null, mentiene el valor original
     * 
     */

    @Modifying
    @Transactional
    @Query("UPDATE Gato g SET " +
            "g.nombre = COALESCE(:nombre, g.nombre), " + 
            "g.nPatas = COALESCE(:nPatas, g.nPatas) " +
            "WHERE g.id = :id")
    int actualizarGatoParcial(Long id, String nombre, Integer nPatas);
}
