package com.suplementos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/suplementos")
public class ServidorApplication {
    
    private List<Suplemento> suplementos = new ArrayList<>();
    private int proximoId = 1;
    
    public static void main(String[] args) {
        SpringApplication.run(ServidorApplication.class, args);
        System.out.println("✅ SERVIDOR RODANDO!");
        System.out.println("✅ URL: http://localhost:8080");
        System.out.println("✅ Endpoint: http://localhost:8080/suplementos");
    }
    
    // listar todos 
    @GetMapping
    public List<Suplemento> listarTodos() {
        return suplementos;
    }
    
    // Buscando por id
    @GetMapping("/{id}")
    public Suplemento buscarPorId(@PathVariable int id) {
        for (Suplemento s : suplementos) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    
    // Cadastrar algo novo
    @PostMapping
    public Suplemento cadastrar(@RequestBody Suplemento novo) {
        novo.setId(proximoId);
        proximoId++;
        suplementos.add(novo);
        System.out.println(" NOVO: " + novo.getNome());
        return novo;
    }
    
    // Atualizar
    @PutMapping("/{id}")
    public Suplemento atualizar(@PathVariable int id, @RequestBody Suplemento atualizado) {
        for (int i = 0; i < suplementos.size(); i++) {
            if (suplementos.get(i).getId() == id) {
                atualizado.setId(id);
                suplementos.set(i, atualizado);
                System.out.println(" ATUALIZADO ID: " + id);
                return atualizado;
            }
        }
        return null;
    }
    
    // Remover
    @DeleteMapping("/{id}")
    public String remover(@PathVariable int id) {
        for (int i = 0; i < suplementos.size(); i++) {
            if (suplementos.get(i).getId() == id) {
                suplementos.remove(i);
                System.out.println(" REMOVIDO ID: " + id);
                return "Removido com sucesso!";
            }
        }
        return "Não encontrado!";
    }
}
