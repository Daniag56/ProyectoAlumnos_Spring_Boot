package com.example.alumnos.repository;

import com.example.alumnos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad {@link Curso}.
 *
 * <p>Extiende {@link JpaRepository} para proporcionar operaciones CRUD completas
 * y consultas automáticas sobre la tabla de cursos.</p>
 *
 * <p>Además, está expuesto como recurso REST mediante Spring Data REST,
 * permitiendo acceder a los cursos a través de endpoints bajo la ruta "/api/cursos".</p>
 */
@RepositoryRestResource(path = "cursos", collectionResourceRel = "cursos")
public interface CursoRepository extends JpaRepository<Curso, Long> {

    /**
     * Busca un curso por su código único.
     *
     * <p>Este método se expone como un endpoint REST adicional bajo la ruta:
     * <pre>/api/cursos/search/por-codigo?codigo=valor</pre></p>
     *
     * @param codigo código único del curso a buscar
     * @return un {@link Optional} que contiene el curso si existe
     */
    @RestResource(path = "por-codigo", rel = "por-codigo")
    Optional<Curso> findByCodigo(@Param("codigo") String codigo);
}

