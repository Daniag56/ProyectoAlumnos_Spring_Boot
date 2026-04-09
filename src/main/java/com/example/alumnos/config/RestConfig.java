package com.example.alumnos.config;

import com.example.alumnos.entity.Alumno;
import com.example.alumnos.entity.Curso;
import com.example.alumnos.entity.Profesor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

/**
 * Configuración personalizada para Spring Data REST.
 *
 * <p>Esta clase permite modificar el comportamiento por defecto de los
 * endpoints REST generados automáticamente por Spring Data REST, incluyendo:</p>
 *
 * <ul>
 *     <li>Definir un prefijo base para todas las rutas REST (por ejemplo, "/api").</li>
 *     <li>Exponer los IDs de las entidades en las respuestas JSON.</li>
 *     <li>Configurar reglas de CORS para permitir peticiones desde otros orígenes.</li>
 * </ul>
 *
 * <p>Se aplica automáticamente gracias a la anotación {@link Configuration}.</p>
 */
@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    /**
     * Configura opciones avanzadas de Spring Data REST, como el prefijo base
     * de los endpoints, la exposición de IDs y las reglas de CORS.
     *
     * @param config objeto de configuración de Spring Data REST
     * @param cors   registro de configuración CORS para permitir accesos externos
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

        // Cambia el prefijo base de todos los endpoints REST a "/api"
        config.setBasePath("/api");

        // Expone los IDs de las entidades en las respuestas JSON
        config.exposeIdsFor(Alumno.class, Curso.class, Profesor.class);

        // Configuración CORS para permitir peticiones desde cualquier origen
        cors.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}

