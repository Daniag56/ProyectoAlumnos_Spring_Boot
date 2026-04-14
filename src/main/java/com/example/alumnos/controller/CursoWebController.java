package com.example.alumnos.controller;

import com.example.alumnos.entity.Curso;
import com.example.alumnos.repository.CursoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cursos")
public class CursoWebController {

    private final CursoRepository cursoRepository;

    public CursoWebController(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // LISTADO DE CURSOS
    @GetMapping("/lista")
    public String listaCursos(Model model) {
        model.addAttribute("listaCursos", cursoRepository.findAll());
        return "cursos-lista";
    }
}
