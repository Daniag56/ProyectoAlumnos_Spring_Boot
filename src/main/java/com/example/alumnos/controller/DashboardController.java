package com.example.alumnos.controller;

import com.example.alumnos.repository.AlumnoRepository;
import com.example.alumnos.repository.CursoRepository;
import com.example.alumnos.repository.ProfesorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class DashboardController {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;

    public DashboardController(AlumnoRepository alumnoRepository,
                               CursoRepository cursoRepository,
                               ProfesorRepository profesorRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
    }

    @GetMapping("/api/dashboard/resumen")
    public ResponseEntity<Map<String, Long>> resumen() {
        Map<String, Long> datos = new LinkedHashMap<>();
        datos.put("alumnos", alumnoRepository.count());
        datos.put("cursos", cursoRepository.count());
        datos.put("profesores", profesorRepository.count());
        return ResponseEntity.ok(datos);
    }
}
