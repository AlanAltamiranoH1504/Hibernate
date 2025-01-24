package altamirano.hernandez.spring_hibernate_jpa_2;

import altamirano.hernandez.spring_hibernate_jpa_2.entities.Persona;
import altamirano.hernandez.spring_hibernate_jpa_2.entities.dto.PersonaDTO;
import altamirano.hernandez.spring_hibernate_jpa_2.repostories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SpringHibernateJpa2Application implements CommandLineRunner {
    //Inyectamos repositorio
    @Autowired
    IPersonaRepository iPersonaRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringHibernateJpa2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        listados();
    }

    public void listados() {
//        List<Persona> personasPorLenguaje = iPersonaRepository.personasPorLenguaje("Java", "Alan");
//        for (var personaEncontrada : personasPorLenguaje) {
//            System.out.println("personaEncontrada = " + personaEncontrada);
//        }
//
//        List<Object[]> obtenerPersonaData = iPersonaRepository.obtenerPersonaData();
//        for (var personaEncontrada : obtenerPersonaData) {
//            System.out.println("Nombre Persona = " + personaEncontrada[0]);
//            System.out.println("Lenguaje de la Persona: = " + personaEncontrada[1]);
//            System.out.println();
//        }

//        List<Object[]> personaDataFul = iPersonaRepository.obtenerPersonaDataFull();
//        for(var persona: personaDataFul){
//            System.out.println("ID: " + persona[0]);
//            System.out.println("Nombre: " + persona[1]);
//            System.out.println("Apellidos: " + persona[2]);
//            System.out.println("Lenguaje de Programacion: " + persona[3]);
//            System.out.println();
//        }

//        List<Object[]>findAllMixPersona = iPersonaRepository.findAllMixPersona();
//        for (var persona: findAllMixPersona){
//            System.out.println("Id persona = " + persona[1]);
//            System.out.println("Persona Completa: " + persona[0]);
//        }

//        List<PersonaDTO>findAllDto = iPersonaRepository.findAllDto();
//        for (var persona: findAllDto) {
//            System.out.println("persona = " + persona);
//            System.out.println();
//        }

//        List<String>findAllNombresUnicos = iPersonaRepository.findAllNombresUnicos();
//        for (var nombre: findAllNombresUnicos){
//            System.out.println("nombre = " + nombre);
//        }

//        List<String>findAllLeguajesUnicos = iPersonaRepository.findAllLenguajesUnicos();
//        for (var lenguaje: findAllLeguajesUnicos){
//            System.out.println("lenguaje = " + lenguaje);
//        }
//
//        int lenguajesUnicos = iPersonaRepository.lenguajesUnicos();
//        System.out.println("lenguajesUnicos = " + lenguajesUnicos);

//        List<String>nombresUpper = iPersonaRepository.nombresUpper();
//        for (var nombre: nombresUpper){
//            System.out.println("nombre = " + nombre);
//        }

//        List<String>nombreLower = iPersonaRepository.nombresLower();
//        for (var name: nombreLower) {
//            System.out.println("name = " + name);
//        }

//        List<Persona>personasBetween = iPersonaRepository.personasBetween(1, 5);
//        for (var persona: personasBetween){
//            System.out.println("persona = " + persona);
//        }

//        List<Persona>personasOrdenadasPorLenguaje = iPersonaRepository.personasOrdenadasPorLenguaje();
//        for (var persona: personasOrdenadasPorLenguaje){
//            System.out.println("persona = " + persona);
//        }

//        List<String[]>personaPorApellido = iPersonaRepository.personaPorApellidos();
//        for (var persona: personaPorApellido){
//            System.out.println("persona = " + persona[0] + " " + persona[1]);
//        }

//        int numeroPersona = iPersonaRepository.numeroPersonas();
//        System.out.println("numeroPersona = " + numeroPersona);
//
//        int idMaximo = iPersonaRepository.idMaximo();
//        System.out.println("idMaximo = " + idMaximo);
//
//        int idMinimo = iPersonaRepository.idMinimo();
//        System.out.println("idMinimo = " + idMinimo);

//        List<Persona>personasNombreMayorA5 = iPersonaRepository.nombreMayorACinco();
//        for (var persona: personasNombreMayorA5){
//            System.out.println("persona = " + persona);
//        }

        Persona persona = iPersonaRepository.personaNombreMasLargo();
        System.out.println("persona = " + persona);
    }

    public void findOne() {
        //Econtramos persona por ID
        Persona persona = iPersonaRepository.findById(1).orElse(null);
        if (persona != null) {
            System.out.println("persona = " + persona);
        } else {
            System.out.println("Persona no encontrada");
        }

        //Econtramos personas por lenguaje
//        List<Persona> personasPorLenguaje = iPersonaRepository.personasPorLenguaje("Java");
//        if (!personasPorLenguaje.isEmpty()){
//            for (var persona: personasPorLenguaje){
//                System.out.println("persona = " + persona);
//            }
//        }

        //Econtramos personas por lenguaje Like
//        List<Persona> personasPorLenguajeLike = iPersonaRepository.personasPorLenguajeLike("Java%");
//        if (!personasPorLenguajeLike.isEmpty()) {
//            for (var persona : personasPorLenguajeLike) {
//                System.out.println("persona = " + persona);
//            }
//        }
    }

    public void insert() {
        //Insertamos nuevo registro
//        Persona persona6 = new Persona("Itzel", "Altamirano Hernandez", "CSS");
//        iPersonaRepository.save(persona6);
//        System.out.println("Persona 6 guardada");

        //Insertamos nuevo registro con datos ingresados
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa el nombre: ");
        var nombre = scanner.nextLine();
        System.out.println("Ingresa los apellidos");
        var apellidos = scanner.nextLine();
        System.out.println("Ingresa el lenguaje de programacion: ");
        var lenguaje = scanner.nextLine();
        Persona persona7 = new Persona(nombre, apellidos, lenguaje);
        Persona persona7Guardada = iPersonaRepository.save(persona7);
        System.out.println("persona7Guardada = " + persona7Guardada);
    }

    public void update(){
//        Persona persona = iPersonaRepository.findById(7).orElse(null);
//        if (persona != null){
//            persona.setApellidos("Altamirano Hernandez");
//            iPersonaRepository.save(persona);
//            System.out.println("Persona Actualizada");
//        }
        Persona persona = iPersonaRepository.findById(1).orElse(null);
        if (persona != null){
            iPersonaRepository.actualizacionLenguajes("Java y Spring", 1);
        }
    }

    public void delete(){
        Persona persona = iPersonaRepository.findById(3).orElse(null);
//        if (persona != null){
//            iPersonaRepository.delete(persona);
//            System.out.println("Persona eliminada");
//        }
        if (persona != null){
            iPersonaRepository.deleteById(persona.getId());
        }
    }
}
