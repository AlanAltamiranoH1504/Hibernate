package altamirano.hernandez.spring_hibernate_jpa_2;

import altamirano.hernandez.spring_hibernate_jpa_2.entities.Persona;
import altamirano.hernandez.spring_hibernate_jpa_2.repostories.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.ObjectError;

import java.util.List;
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
        delete();
    }

    @Transactional(readOnly = true)
    public void listados() {
        List<Persona> personasPorLenguaje = iPersonaRepository.personasPorLenguaje("Java", "Alan");
        for (var personaEncontrada : personasPorLenguaje) {
            System.out.println("personaEncontrada = " + personaEncontrada);
        }

        List<Object[]> obtenerPersonaData = iPersonaRepository.obtenerPersonaData();
        for (var personaEncontrada : obtenerPersonaData) {
            System.out.println("Nombre Persona = " + personaEncontrada[0]);
            System.out.println("Lenguaje de la Persona: = " + personaEncontrada[1]);
            System.out.println();
        }
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = false)
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

    @Transactional(readOnly = false)
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

    @Transactional(readOnly = false)
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
