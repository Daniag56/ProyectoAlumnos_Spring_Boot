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

/**
 * Controlador MVC encargado de gestionar las vistas del dashboard
 * y las operaciones relacionadas con alumnos dentro de la interfaz web.
 *
 * <p>Este controlador utiliza Thymeleaf para renderizar las páginas HTML
 * y proporciona datos necesarios para mostrar estadísticas y formularios.</p>
 */
@Controller
public class DashboardWebController {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;

    /**
     * Constructor que inyecta los repositorios necesarios para obtener
     * información de alumnos, cursos y profesores.
     *
     * @param alumnoRepository   repositorio de alumnos
     * @param cursoRepository    repositorio de cursos
     * @param profesorRepository repositorio de profesores
     */
    public DashboardWebController(AlumnoRepository alumnoRepository,
                                  CursoRepository cursoRepository,
                                  ProfesorRepository profesorRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
    }

    /**
     * Muestra la página principal del dashboard.
     *
     * <p>Este método carga estadísticas generales como el número total
     * de alumnos, cursos y profesores, además de la lista completa de cursos
     * para mostrarlos en la tabla del dashboard.</p>
     *
     * @param model objeto {@link Model} utilizado para enviar datos a la vista
     * @return nombre de la plantilla Thymeleaf a renderizar ("dashboard")
     */
    @GetMapping("/dashboard")
    public String verDashboard(Model model) {

        model.addAttribute("totalAlumnos", alumnoRepository.count());
        model.addAttribute("totalCursos", cursoRepository.count());
        model.addAttribute("totalProfesores", profesorRepository.count());

        model.addAttribute("listaCursos", cursoRepository.findAll());

        return "dashboard";
    }

    /**
     * Muestra el formulario para crear un nuevo alumno.
     *
     * <p>Se envía un objeto vacío de {@link Alumno} y la lista de cursos
     * disponibles para que el usuario pueda asignar uno.</p>
     *
     * @param model objeto {@link Model} utilizado para enviar datos a la vista
     * @return nombre de la plantilla Thymeleaf ("alumno-form")
     */
    @GetMapping("/alumnos/nuevo")
    public String nuevoAlumno(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("cursos", cursoRepository.findAll());
        return "alumno-form";
    }

    /**
     * Guarda un nuevo alumno en la base de datos.
     *
     * <p>Si el alumno tiene un curso asociado, se recupera desde el repositorio
     * para asegurar que la relación esté correctamente gestionada por JPA.</p>
     *
     * @param alumno objeto {@link Alumno} recibido desde el formulario
     * @return redirección al dashboard tras guardar los datos
     */
    @PostMapping("/alumnos/guardar")
    public String guardarAlumno(Alumno alumno) {

        // Si el alumno tiene un curso seleccionado, se recupera de la BD
        if (alumno.getCurso() != null && alumno.getCurso().getId() != null) {
            Curso curso = cursoRepository.findById(alumno.getCurso().getId()).orElse(null);
            alumno.setCurso(curso);
        }

        alumnoRepository.save(alumno);
        return "redirect:/dashboard";
    }
}
