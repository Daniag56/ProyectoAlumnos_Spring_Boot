package com.example.alumnos.controller;

import com.example.alumnos.entity.Curso;
import com.example.alumnos.entity.Profesor;
import com.example.alumnos.repository.CursoRepository;
import com.example.alumnos.repository.ProfesorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profesores")
public class ProfesorWebController {

    private final ProfesorRepository profesorRepository;
    private final CursoRepository cursoRepository;

    public ProfesorWebController(ProfesorRepository profesorRepository,
                                 CursoRepository cursoRepository) {
        this.profesorRepository = profesorRepository;
        this.cursoRepository = cursoRepository;
    }

    // LISTADO
    @GetMapping("/lista")
    public String listaProfesores(Model model) {
        model.addAttribute("listaProfesores", profesorRepository.findAll());
        return "profesores-lista";
    }

    // NUEVO PROFESOR
    @GetMapping("/nuevo")
    public String nuevoProfesor(@RequestParam(required = false) Long cursoId, Model model) {
        Profesor profesor = new Profesor();
        model.addAttribute("profesor", profesor);
        model.addAttribute("cursoId", cursoId);
        return "profesor-form";
    }

    @PostMapping("/guardar")
    public String guardarProfesor(Profesor profesor,
                                  @RequestParam(required = false) Long cursoId) {

        Profesor guardado = profesorRepository.save(profesor);

        if (cursoId != null) {
            Curso curso = cursoRepository.findById(cursoId).orElse(null);
            if (curso != null) {
                curso.setProfesor(guardado);
                cursoRepository.save(curso);
            }
        }

        return "redirect:/profesores/lista";
    }

    // EDITAR PROFESOR
    @GetMapping("/editar/{id}")
    public String editarProfesor(@PathVariable Long id, Model model) {
        Profesor profesor = profesorRepository.findById(id).orElse(null);
        model.addAttribute("profesor", profesor);
        return "profesor-form";
    }

    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, Profesor profesorActualizado) {

        Profesor profesor = profesorRepository.findById(id).orElse(null);

        if (profesor != null) {
            profesor.setNombre(profesorActualizado.getNombre());
            profesor.setEmail(profesorActualizado.getEmail());
            profesor.setDepartamento(profesorActualizado.getDepartamento());
            profesorRepository.save(profesor);
        }

        return "redirect:/profesores/lista";
    }

    // ELIMINAR PROFESOR
    @GetMapping("/eliminar/{id}")
    public String eliminarProfesor(@PathVariable Long id) {

        cursoRepository.findAll().forEach(curso -> {
            if (curso.getProfesor() != null && curso.getProfesor().getId().equals(id)) {
                curso.setProfesor(null);
                cursoRepository.save(curso);
            }
        });

        profesorRepository.deleteById(id);

        return "redirect:/profesores/lista";
    }
}
