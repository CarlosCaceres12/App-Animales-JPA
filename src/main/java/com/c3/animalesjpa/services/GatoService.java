package com.c3.animalesjpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.c3.animalesjpa.entities.Gato;
import com.c3.animalesjpa.repositories.GatoRepository;

@Service
public class GatoService {

    @Autowired
    private GatoRepository gatoRepository;

    public List<Gato> obtenerTodoGatos() {
        return gatoRepository.findAll();
    }

    public Gato buscarGatoPorId(Long id) {
        return gatoRepository.findById(id).orElse(null);
    }

    public List<Gato> buscarPorNombre(String nombre) {
        return gatoRepository.findByNombre(nombre);
    }

    public Gato guardarGato(Gato gato) {
        return gatoRepository.save(gato); // insert into .... values ...
    }

    public void eliminarGato(Long id) {
        gatoRepository.deleteById(id);
    }

    public boolean actualizarGatoParcial(Long id, Gato gatoActualizado) {
        int filasActualizadas = gatoRepository.actualizarGatoParcial(
            id,
            gatoActualizado.getNombre(),
            gatoActualizado.getNPatas());

        return filasActualizadas > 0;
    }


    public Gato buscarPorId(Long id) {
        return gatoRepository.findById(id).orElse(null);
    }

    public List<Gato> buscarPorNombre(String nombre) {
        return gatoRepository.findByNombre(nombre);
    }

}