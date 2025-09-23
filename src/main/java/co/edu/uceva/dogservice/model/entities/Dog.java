package co.edu.uceva.dogservice.model.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String raza;
    private Integer edad;
    private Double peso;
    private String color;
    private String sexo; // "Macho" o "Hembra"
    private Boolean vacunado;
    private Boolean esterilizado;

}