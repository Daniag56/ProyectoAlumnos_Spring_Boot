package com.example.alumnos.repository;

import com.example.alumnos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "cursos", collectionResourceRel = "cursos")
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @RestResource(path = "por-codigo", rel = "por-codigo")
    Optional<Curso> findByCodigo(@Param("codigo") String codigo);
}
