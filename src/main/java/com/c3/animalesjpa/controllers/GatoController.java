package com.c3.animalesjpa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.c3.animalesjpa.entities.Gato;
import com.c3.animalesjpa.repositories.GatoRepository;
import com.c3.animalesjpa.services.GatoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/gatos")
public class GatoController {


     @Autowired
     private GatoService gatoService;

     // Devolver todos los gatos
     @GetMapping
     public ResponseEntity<List<Gato>> obtenerTodosGatos() {
        List<Gato> gatos = gatoService.obtenerTodoGatos();
        return gatos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(gatos);
     }

     // Buscar por el nombre
     @GetMapping("/buscar")
     public ResponseEntity<List<Gato>> buscarPorNombre(@RequestParam String nombre) {
        List<Gato> gatos = gatoService.buscarPorNombre(nombre);
        return gatos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(gatos);
     }
     
     //Buscar por ID
     @GetMapping("/{id}")
     public ResponseEntity<Gato> buscarPorId(@PathVariable Long id) {
        Gato gato = gatoService.buscarGatoPorId(id);
        return gato != null ? ResponseEntity.ok(gato) : ResponseEntity.notFound().build();
     }

     // Guardar un nuevo gato
     @PostMapping("/guardar")
     public ResponseEntity<Gato> guardarGato(@RequestBody Gato gato) {
         Gato gatoNuevo = gatoService.guardarGato(gato);
         
         return ResponseEntity.ok(gatoNuevo);
     }
     
     // Eliminar por id
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> eliminarGato(@PathVariable Long id) {
      if (gatoService.obtenerTodoGatos().stream().noneMatch(g -> g.getId().equals(id))) {
         return ResponseEntity.notFound().build();
      }

      gatoService.eliminarGato(id);
      return ResponseEntity.noContent().build();
     }

     // Endpoind del patch
     @PatchMapping("/{id}")
     public ResponseEntity<String> actualizarGatoParcial(
         @PathVariable Long id, 
         @RequestBody Gato gatoActualizado) {
            
            boolean actualizado = gatoService.actualizarGatoParcial(id, gatoActualizado);
            return actualizado ? 
                  ResponseEntity.ok("Gato actualizado con éxito.") :
                  ResponseEntity.notFound().build();
      }


    @GetMapping("/{id}")
    public ResponseEntity<Gato> buscarPorId(@PathVariable Long id){
        Gato gato = gatoService.buscarPorId(id);
        return gato != null ? ResponseEntity.ok(gato) : ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public List<Gato> buscarPorNombre(@RequestParam String nombre){
        return gatoService.buscarPorNombre(nombre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarGato(@PathVariable Long id){
        gatoService.eliminarGato(id);
        return ResponseEntity.ok("Gato eliminado");
    }

}