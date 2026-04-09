package com.example.alumnos.controller;

import com.example.alumnos.repository.AlumnoRepository;
import com.example.alumnos.repository.CursoRepository;
import com.example.alumnos.repository.ProfesorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Controlador REST encargado de proporcionar datos resumidos
 * para el panel de control (dashboard) de la aplicación.
 *
 * <p>Este controlador expone un endpoint que devuelve un resumen
 * con el número total de alumnos, cursos y profesores registrados
 * en la base de datos.</p>
 *
 * <p>Se utiliza principalmente para integraciones AJAX o clientes externos
 * que necesiten obtener estadísticas globales.</p>
 */
@RestController
public class DashboardController {

    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final ProfesorRepository profesorRepository;

    /**
     * Constructor que inyecta los repositorios necesarios para obtener
     * los datos del resumen.
     *
     * @param alumnoRepository   repositorio de alumnos
     * @param cursoRepository    repositorio de cursos
     * @param profesorRepository repositorio de profesores
     */
    public DashboardController(AlumnoRepository alumnoRepository,
                               CursoRepository cursoRepository,
                               ProfesorRepository profesorRepository) {
        this.alumnoRepository = alumnoRepository;
        this.cursoRepository = cursoRepository;
        this.profesorRepository = profesorRepository;
    }

    /**
     * Devuelve un resumen con el número total de alumnos, cursos y profesores.
     *
     * <p>La respuesta se entrega en formato JSON con claves ordenadas
     * utilizando un {@link LinkedHashMap}.</p>
     *
     * @return {@code ResponseEntity} con un mapa que contiene:
     * <ul>
     *     <li>"alumnos": cantidad total de alumnos</li>
     *     <li>"cursos": cantidad total de cursos</li>
     *     <li>"profesores": cantidad total de profesores</li>
     * </ul>
     */
    @GetMapping("/api/dashboard/resumen")
    public ResponseEntity<Map<String, Long>> resumen() {
        Map<String, Long> datos = new LinkedHashMap<>();
        datos.put("alumnos", alumnoRepository.count());
        datos.put("cursos", cursoRepository.count());
        datos.put("profesores", profesorRepository.count());
        return ResponseEntity.ok(datos);
    }
}
