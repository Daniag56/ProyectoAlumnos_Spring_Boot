package com.example.alumnos.controller;

import com.example.alumnos.entity.Profesor;
import com.example.alumnos.repository.ProfesorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC encargado de gestionar las operaciones relacionadas
 * con profesores dentro de la interfaz web.
 *
 * <p>Este controlador permite editar y eliminar profesores desde la vista
 * del dashboard, utilizando plantillas Thymeleaf para mostrar los formularios.</p>
 */
@Controller
@RequestMapping("/profesores")
public class ProfesorWebController {

    private final ProfesorRepository profesorRepository;

    /**
     * Constructor que inyecta el repositorio de profesores.
     *
     * @param profesorRepository repositorio utilizado para acceder y modificar
     *                           los datos de profesores en la base de datos
     */
    public ProfesorWebController(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    /**
     * Muestra el formulario de edición de un profesor.
     *
     * <p>El método busca el profesor por su ID y lo envía a la vista
     * {@code profesor-form.html} para que el usuario pueda modificar sus datos.</p>
     *
     * @param id    identificador del profesor a editar
     * @param model objeto {@link Model} utilizado para enviar datos a la vista
     * @return nombre de la plantilla Thymeleaf ("profesor-form")
     */
    @GetMapping("/editar/{id}")
    public String editarProfesor(@PathVariable Long id, Model model) {
        Profesor profesor = profesorRepository.findById(id).orElse(null);
        model.addAttribute("profesor", profesor);
        return "profesor-form";
    }

    /**
     * Guarda los cambios realizados en un profesor existente.
     *
     * <p>El método recupera el profesor original, actualiza sus campos con los
     * valores enviados desde el formulario y lo guarda en la base de datos.</p>
     *
     * @param id                 identificador del profesor a actualizar
     * @param profesorActualizado objeto {@link Profesor} con los datos modificados
     * @return redirección al dashboard tras guardar los cambios
     */
    @PostMapping("/editar/{id}")
    public String guardarEdicion(@PathVariable Long id, Profesor profesorActualizado) {

        Profesor profesor = profesorRepository.findById(id).orElse(null);
        if (profesor != null) {
            profesor.setNombre(profesorActualizado.getNombre());
            profesor.setEmail(profesorActualizado.getEmail());
            profesor.setDepartamento(profesorActualizado.getDepartamento());
            profesorRepository.save(profesor);
        }

        return "redirect:/dashboard";
    }

    /**
     * Elimina un profesor de la base de datos.
     *
     * <p>Este método elimina el profesor cuyo ID se recibe en la URL
     * y redirige nuevamente al dashboard.</p>
     *
     * @param id identificador del profesor a eliminar
     * @return redirección al dashboard tras eliminar el profesor
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarProfesor(@PathVariable Long id) {
        profesorRepository.deleteById(id);
        return "redirect:/dashboard";
    }
}
