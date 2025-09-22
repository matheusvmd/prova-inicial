package com.example.prova_inicial.controller;

import com.example.prova_inicial.model.Curso;
import com.example.prova_inicial.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    // Criar curso
    @PostMapping
    public Curso criarCurso(@RequestBody Curso curso) {
        curso.setDataCadastro(LocalDate.now());
        return repository.save(curso);
    }

    // Listar cursos
    @GetMapping
    public List<Curso> listarCursos() {
        return repository.findAll();
    }

    // Deletar curso
    @DeleteMapping("/{id}")
    public void deletarCurso(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
