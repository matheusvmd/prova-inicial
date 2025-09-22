package com.example.prova_inicial.controller;

import com.example.prova_inicial.model.Curso;
import com.example.prova_inicial.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CursoControllerTest {

    @InjectMocks
    private CursoController cursoController;

    @Mock
    private CursoRepository cursoRepository;

    private MockMvc mockMvc;
    private Curso curso;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cursoController).build();

        curso = new Curso();
        curso.setId(1L);
        curso.setTitulo("Java Básico");
        curso.setDescricao("Introdução ao Java");
        curso.setCargaHoraria(40);
        curso.setInstrutor("Matheus");
        curso.setDataCadastro(LocalDate.now());
    }

    @Test
    public void test_listarCursosShouldReturnOneCurso() throws Exception {
        Mockito.when(cursoRepository.findAll()).thenReturn(List.of(curso));

        mockMvc.perform(MockMvcRequestBuilders.get("/cursos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo").value("Java Básico"));
    }

    @Test
    public void test_criarCursoShouldReturnSavedCurso() throws Exception {
        Mockito.when(cursoRepository.save(any(Curso.class))).thenReturn(curso);

        mockMvc.perform(MockMvcRequestBuilders.post("/cursos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titulo\":\"Java Básico\",\"descricao\":\"Intro\",\"cargaHoraria\":40,\"instrutor\":\"Matheus\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo").value("Java Básico"));
    }

    @Test
    public void test_deletarCursoShouldReturn200() throws Exception {
        Mockito.doNothing().when(cursoRepository).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/cursos/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
