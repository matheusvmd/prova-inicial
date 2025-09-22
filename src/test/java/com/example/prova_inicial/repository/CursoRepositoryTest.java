package com.example.prova_inicial.repository;

import com.example.prova_inicial.model.Curso;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test") // força usar o H2
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository cursoRepository;

    @Test
    public void test_saveAndFindCurso() {
        Curso curso = new Curso();
        curso.setTitulo("Spring Boot");
        curso.setDescricao("API REST com Spring");
        curso.setCargaHoraria(30);
        curso.setInstrutor("Matheus");
        curso.setDataCadastro(LocalDate.now());

        Curso saved = cursoRepository.save(curso);

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitulo()).isEqualTo("Spring Boot");
    }
}
