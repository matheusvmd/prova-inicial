package com.example.prova_inicial.repository;

import com.example.prova_inicial.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
