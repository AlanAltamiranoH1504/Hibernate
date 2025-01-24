package altamirano.hernandez.spring_hibernate_jpa_2.entities.dto;

public class PersonaDTO {
    private String nombre;
    private String apellidos;
    private String lenguaje;

    public PersonaDTO(){}
    public PersonaDTO(String nombre, String apellidos, String lenguajeDeProgramacion){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.lenguaje = lenguajeDeProgramacion;
    }

    //Get y Set
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
    public String getLenguaje() {
        return lenguaje;
    }
    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    //toString
    @Override
    public String toString() {
        return "PersonaDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", lenguaje='" + lenguaje + '\'' +
                '}';
    }
}
