package com.example.alumnos.repository;

import com.example.alumnos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Alumno}.
 *
 * <p>Extiende {@link JpaRepository} para proporcionar operaciones CRUD completas
 * y consultas automáticas sobre la tabla de alumnos.</p>
 *
 * <p>Además, está expuesto como recurso REST mediante Spring Data REST,
 * permitiendo acceder a los alumnos a través de endpoints bajo la ruta "/api/alumnos".</p>
 */
@RepositoryRestResource(path = "alumnos", collectionResourceRel = "alumnos")
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    /**
     * Busca un alumno por su correo electrónico.
     *
     * <p>Este método se expone como un endpoint REST adicional bajo la ruta:
     * <pre>/api/alumnos/search/por-email?email=valor</pre></p>
     *
     * @param email correo electrónico del alumno a buscar
     * @return un {@link Optional} que contiene el alumno si existe
     */
    @RestResource(path = "por-email", rel = "por-email")
    Optional<Alumno> findByEmail(@Param("email") String email);
}
