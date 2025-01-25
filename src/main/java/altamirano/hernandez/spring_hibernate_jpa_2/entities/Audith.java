package altamirano.hernandez.spring_hibernate_jpa_2.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

//Clase de tipo Embebible
@Embeddable
public class Audith {
    @Column(name = "creat_at")
    private LocalDateTime creatAt;
    @Column(name = "update_at")
    private LocalDateTime updatetAt;
    @Column(name = "delete_at")
    private LocalDateTime deletetAt;


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

    public LocalDateTime getCreatAt() {
        return creatAt;
    }
    public void setCreatAt(LocalDateTime creatAt) {
        this.creatAt = creatAt;
    }
    public LocalDateTime getUpdatetAt() {
        return updatetAt;
    }
    public void setUpdatetAt(LocalDateTime updatetAt) {
        this.updatetAt = updatetAt;
    }
    public LocalDateTime getDeletetAt() {
        return deletetAt;
    }
    public void setDeletetAt(LocalDateTime deletetAt) {
        this.deletetAt = deletetAt;
    }
}
