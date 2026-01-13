package com.suplementos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/suplementos")
public class ServidorApplication {
    
    // Lista sincronizada para evitar erros com múltiplos clientes simultâneos
    private List<Suplemento> suplementos = Collections.synchronizedList(new ArrayList<>());
    private int proximoId = 1;
    
    public static void main(String[] args) {
        SpringApplication.run(ServidorApplication.class, args);
        System.out.println("✅ SERVIDOR DE SUPLEMENTOS RODANDO!");
        System.out.println("✅ Endpoint: http://localhost:8080/suplementos");
    }
    
    
    @GetMapping
    public List<Suplemento> listarTodos() {
        return suplementos;
    }
    
    // Buscar por ID com tratamento de erro 404
    @GetMapping("/{id}")
    public ResponseEntity<Suplemento> buscarPorId(@PathVariable int id) {
        return suplementos.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Cadastrar com status
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Suplemento cadastrar(@RequestBody Suplemento novo) {
        novo.setId(proximoId++);
        suplementos.add(novo);
        System.out.println("NOVO SUPLEMENTO CADASTRADO: " + novo.getNome());
        return novo;
    }
    
    // Atualizar com tratamento de erro
    @PutMapping("/{id}")
    public ResponseEntity<Suplemento> atualizar(@PathVariable int id, @RequestBody Suplemento atualizado) {
        for (int i = 0; i < suplementos.size(); i++) {
            if (suplementos.get(i).getId() == id) {
                atualizado.setId(id);
                suplementos.set(i, atualizado);
                return ResponseEntity.ok(atualizado);
            }
        }
        return ResponseEntity.notFound().build();
    }
    
    // Remover com status
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        boolean removido = suplementos.removeIf(s -> s.getId() == id);
        if (removido) {
            System.out.println("REMOVIDO ID: " + id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}