package com.example.alumnos.controller;

import com.example.alumnos.entity.Alumno;
import com.example.alumnos.entity.Curso;
import com.example.alumnos.repository.AlumnoRepository;
import com.example.alumnos.repository.CursoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumnos")
public class AlumnoWebController {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;

    public AlumnoWebController(AlumnoRepository alumnoRepository,
                               CursoRepository cursoRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
    }


    @GetMapping("/lista")
    public String listaAlumnos(Model model) {
        model.addAttribute("listaAlumnos", alumnoRepository.findAll());
        return "alumnos-lista";
    }


    @GetMapping("/nuevo")
    public String nuevoAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "alumno-form";
    }

    @PostMapping("/guardar")
    public String guardarAlumno(Alumno alumno) {


        if (alumno.getCurso() != null && alumno.getCurso().getId() != null) {
            Curso cursoReal = cursoRepository.findById(alumno.getCurso().getId()).orElse(null);
            alumno.setCurso(cursoReal);
        } else {
            alumno.setCurso(null);
        }

        alumnoRepository.save(alumno);
        return "redirect:/alumnos/lista";
    }

    // EDITAR ALUMNO
    @GetMapping("/editar/{id}")
    public String editarAlumno(@PathVariable Long id, Model model) {
        Alumno alumno = alumnoRepository.findById(id).orElse(null);
        model.addAttribute("alumno", alumno);
        model.addAttribute("cursos", cursoRepository.findAll());
        return "alumno-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, Alumno alumnoActualizado) {

        Alumno alumno = alumnoRepository.findById(id).orElse(null);

        if (alumno != null) {
            alumno.setNombre(alumnoActualizado.getNombre());
            alumno.setApellidos(alumnoActualizado.getApellidos());
            alumno.setEmail(alumnoActualizado.getEmail());
            alumno.setFechaNacimiento(alumnoActualizado.getFechaNacimiento());


            if (alumnoActualizado.getCurso() != null && alumnoActualizado.getCurso().getId() != null) {
                Curso cursoReal = cursoRepository.findById(alumnoActualizado.getCurso().getId()).orElse(null);
                alumno.setCurso(cursoReal);
            } else {
                alumno.setCurso(null);
            }

            alumnoRepository.save(alumno);
        }

        return "redirect:/alumnos/lista";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id) {
        alumnoRepository.deleteById(id);
        return "redirect:/alumnos/lista";
    }
}
