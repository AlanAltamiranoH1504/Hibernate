package altamirano.hernandez.spring_hibernate_jpa_2.repostories;

import altamirano.hernandez.spring_hibernate_jpa_2.entities.Persona;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPersonaRepository extends CrudRepository<Persona, Integer> {

    //Personas por lenguaje de programacion
    @Query("SELECT p FROM Persona p WHERE p.lenguajeDeProgramacion =:lenguaje AND p.nombre =:nombre")
    List<Persona> personasPorLenguaje(@Param("lenguaje") String lenguaje, @Param("nombre") String nombre);

    //Metodo que trae atributos especificos
    @Query("SELECT p.nombre, p.lenguajeDeProgramacion FROM Persona p")
    List<Object[]>obtenerPersonaData();

    //Personas por lenguaje
    @Query("SELECT p FROM Persona p WHERE p.lenguajeDeProgramacion =:lenguaje")
    List<Persona> personasPorLenguaje(@Param("lenguaje") String lenguaje);

    //Persona por lenguaje con like
    @Query("SELECT p FROM Persona p WHERE p.lenguajeDeProgramacion LIKE:lenguaje")
    List<Persona> personasPorLenguajeLike(@Param("lenguaje") String lenguaje);

    // Actualiza los lenguajes
    @Modifying
    @Transactional(readOnly = false)
    @Query("UPDATE Persona p SET p.lenguajeDeProgramacion =:lenguajes WHERE p.id =:id")
    void actualizacionLenguajes(@Param("lenguajes")String lenguajes, @Param("id") int id);
}
