package com.example.alumnos.repository;

import com.example.alumnos.entity.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(path = "alumnos", collectionResourceRel = "alumnos")
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @RestResource(path = "por-email", rel = "por-email")
    Optional<Alumno> findByEmail(@Param("email") String email);
}

