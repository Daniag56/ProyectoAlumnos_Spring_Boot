package com.example.alumnos.controller;

import com.example.alumnos.entity.Alumno;
import com.example.alumnos.entity.Curso;
import com.example.alumnos.repository.AlumnoRepository;
import com.example.alumnos.repository.CursoRepository;
import com.example.alumnos.repository.ProfesorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

        model.addAttribute("listaCursos", cursoRepository.findAll());

        return "dashboard";
    }


    @GetMapping("/alumnos/nuevo")
    public String nuevoAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "alumno-form";
    }


    @PostMapping("/alumnos/guardar")
    public String guardarAlumno(Alumno alumno) {

        // Convertir el ID recibido en un Curso real
        if (alumno.getCurso() != null && alumno.getCurso().getId() != null) {
            Curso curso = cursoRepository.findById(alumno.getCurso().getId()).orElse(null);
            alumno.setCurso(curso);
        }

        alumnoRepository.save(alumno);
        return "redirect:/dashboard";
    }
}
