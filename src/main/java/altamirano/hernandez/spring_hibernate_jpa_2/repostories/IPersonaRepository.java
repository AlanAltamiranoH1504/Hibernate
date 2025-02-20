package altamirano.hernandez.spring_hibernate_jpa_2.repostories;

import altamirano.hernandez.spring_hibernate_jpa_2.entities.Persona;
import altamirano.hernandez.spring_hibernate_jpa_2.entities.dto.PersonaDTO;
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
    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT p.lenguajeDeProgramacion FROM Persona p")
    List<String>findAllLenguajesUnicos();

    //Cantidad de lenguajes unicos
    @Transactional(readOnly = true)
    @Query("SELECT COUNT (DISTINCT p.lenguajeDeProgramacion) FROM Persona p")
    int lenguajesUnicos();

    //Nombres en Mayusculas
    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT UPPER(CONCAT(p.nombre, ' ', p.apellidos)) FROM Persona p")
    List<String>nombresUpper();

    //Nombres en Minusculas
    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT LOWER(CONCAT(p.nombre, ' ', p.apellidos)) FROM Persona p")
    List<String>nombresLower();

    //Personas entre el id 1 y 5
    @Transactional(readOnly = true)
    @Query("SELECT p FROM Persona p WHERE p.id BETWEEN :n1 AND :n2 ")
    List<Persona> personasBetween(@Param("n1")int n1, @Param("n2") int n2);

    //Ordenado por lenguaje de manera ascendente
    @Transactional(readOnly = true)
    @Query("SELECT p FROM Persona p ORDER BY p.lenguajeDeProgramacion")
    List<Persona> personasOrdenadasPorLenguaje();

    //Ordenadmo por Apellidos
    @Transactional(readOnly = true)
    @Query("SELECT p.nombre, p.apellidos FROM Persona p ORDER BY p.apellidos")
    List<String[]>personaPorApellidos();

    //Contamos numero de usuarios
    @Transactional(readOnly = true)
    @Query("SELECT COUNT (p) FROM Persona p")
    int numeroPersonas();

    //Id Maximo
    @Transactional(readOnly = true)
    @Query("SELECT MAX (p.id) FROM Persona p")
    int idMaximo();

    // Id Minimo
    @Transactional(readOnly = true)
    @Query("SELECT MIN (p.id) FROM Persona p")
    int idMinimo();

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Persona p WHERE LENGTH(p.nombre) <= 5")
    List<Persona>nombreMayorACinco();

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Persona p ORDER BY LENGTH(p.apellidos) ASC LIMIT 1")
    Persona personaNombreMasLargo();

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Persona p WHERE LENGTH(p.apellidos) = (SELECT MAX(length(sub.apellidos) ) FROM Persona sub)")
    Persona personaNombreMasLargo2();
}
