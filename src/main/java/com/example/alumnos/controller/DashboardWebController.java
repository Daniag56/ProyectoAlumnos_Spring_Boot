package com.example.alumnos.controller;

import com.example.alumnos.repository.AlumnoRepository;
import com.example.alumnos.repository.CursoRepository;
import com.example.alumnos.repository.ProfesorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardWebController {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;

    public DashboardWebController(AlumnoRepository alumnoRepository,
                                  CursoRepository cursoRepository,
                                  ProfesorRepository profesorRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
    }

    @GetMapping("/dashboard")
    public String verDashboard(Model model) {
        model.addAttribute("totalAlumnos", alumnoRepository.count());
        model.addAttribute("totalCursos", cursoRepository.count());
        model.addAttribute("totalProfesores", profesorRepository.count());
        return "dashboard";
    }
}
