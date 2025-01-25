package altamirano.hernandez.spring_hibernate_jpa_2.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String apellidos;
    @Column(name = "lenguaje_de_programacion")
    private String lenguajeDeProgramacion;
    @Column(name = "creat_at")
    private LocalDateTime creatAt;
    @Column(name = "update_at")
    private LocalDateTime updatetAt;
    @Column(name = "delete_at")
    private LocalDateTime deletetAt;

    //Constructores
    public Persona() {
    }

    public Persona(String nombre, String apellidos, String lenguajeDeProgramacion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.lenguajeDeProgramacion = lenguajeDeProgramacion;
    }

    //Metodos del ciclo de vida
    @PrePersist
    public void prePersist(){
        System.out.println("Evento del ciclo de vida del Entity. Antes de guardarlo en la DB");
    }
    @PostPersist
    public void postPersist(){
        System.out.println("Evento del ciclo de vida del Entity. Despues de guardarlo en la DB");
        this.creatAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        System.out.println("Evento del ciclo de vida del Entity. Antes de actualizarlo en la DB");
    }
    @PostUpdate
    public void postUpdate(){
        System.out.println("Evento del ciclo de vida del Entity. Despues de actualizarlo en la DB");
        this.updatetAt = LocalDateTime.now();
    }
    //Metodos Get y Set
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getLenguajeDeProgramacion() {
        return lenguajeDeProgramacion;
    }
    public void setLenguajeDeProgramacion(String lenguajeDeProgramacion) {
        this.lenguajeDeProgramacion = lenguajeDeProgramacion;
    }

    //Equals y Hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return id == persona.id && Objects.equals(nombre, persona.nombre) && Objects.equals(apellidos, persona.apellidos) && Objects.equals(lenguajeDeProgramacion, persona.lenguajeDeProgramacion);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, lenguajeDeProgramacion);
    }

    //toString
    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", lenguajeDeProgramacion='" + lenguajeDeProgramacion + '\'' +
                '}';
    }
}
