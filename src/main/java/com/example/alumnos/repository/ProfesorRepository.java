package com.example.alumnos.repository;

import com.example.alumnos.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Profesor}.
 *
 * <p>Extiende {@link JpaRepository} para proporcionar operaciones CRUD completas
 * y consultas automáticas sobre la tabla de profesores.</p>
 *
 * <p>Además, está expuesto como recurso REST mediante Spring Data REST,
 * permitiendo acceder a los profesores a través de endpoints bajo la ruta
 * <strong>/api/profesores</strong>.</p>
 */
@RepositoryRestResource(path = "profesores", collectionResourceRel = "profesores")
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

    /**
     * Busca un profesor por su correo electrónico.
     *
     * <p>Este método se expone como un endpoint REST adicional bajo la ruta:
     * <pre>/api/profesores/search/por-email?email=valor</pre></p>
     *
     * @param email correo electrónico del profesor a buscar
     * @return un {@link Optional} que contiene el profesor si existe
     */
    @RestResource(path = "por-email", rel = "por-email")
    Optional<Profesor> findByEmail(@Param("email") String email);
}
