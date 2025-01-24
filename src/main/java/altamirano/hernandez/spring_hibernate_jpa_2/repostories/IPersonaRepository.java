package altamirano.hernandez.spring_hibernate_jpa_2.repostories;

import altamirano.hernandez.spring_hibernate_jpa_2.entities.Persona;
import altamirano.hernandez.spring_hibernate_jpa_2.entities.dto.PersonaDTO;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    //Todos los atributos pero separados
    @Transactional(readOnly = true)
    @Query("SELECT p.id, p.nombre, p.apellidos, p.lenguajeDeProgramacion FROM Persona p")
    List<Object[]>obtenerPersonaDataFull();

    //Objeto Persona completo y campos especificos
    @Transactional(readOnly = true)
    @Query("SELECT p, p.id FROM Persona p")
    List<Object[]>findAllMixPersona();

    //Metodo que devuelve instancias DTO
    @Transactional(readOnly = true)
    @Query("SELECT new altamirano.hernandez.spring_hibernate_jpa_2.entities.dto.PersonaDTO(p.nombre, p.apellidos, p.lenguajeDeProgramacion) FROM Persona p")
    List<PersonaDTO>findAllDto();

    //Lista los nombres de todas las personas pero una sola vez el nombre
    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT p.nombre FROM Persona p")
    List<String>findAllNombresUnicos();

    //Lista los lenguaje de prrogramacion unicos
    @Query("SELECT DISTINCT p.lenguajeDeProgramacion FROM Persona p")
    List<String>findAllLenguajesUnicos();

    //Cantidad de lenguajes unicos
    @Query("SELECT COUNT (DISTINCT p.lenguajeDeProgramacion) FROM Persona p")
    int lenguajesUnicos();

    //Nombres en Mayusculas
    @Query("SELECT DISTINCT UPPER(CONCAT(p.nombre, ' ', p.apellidos)) FROM Persona p")
    List<String>nombresUpper();

    //Nombres en Minusculas
    @Query("SELECT DISTINCT LOWER(CONCAT(p.nombre, ' ', p.apellidos)) FROM Persona p")
    List<String>nombresLower();

    //Personas entre el id 1 y 5
    @Query("SELECT p FROM Persona p WHERE p.id BETWEEN :n1 AND :n2 ")
    List<Persona> personasBetween(@Param("n1")int n1, @Param("n2") int n2);

    //Ordenado por lenguaje de manera ascendente
    @Query("SELECT p FROM Persona p ORDER BY p.lenguajeDeProgramacion")
    List<Persona> personasOrdenadasPorLenguaje();

    //Ordenadmo por Apellidos
    @Query("SELECT p.nombre, p.apellidos FROM Persona p ORDER BY p.apellidos")
    List<String[]>personaPorApellidos();
}
