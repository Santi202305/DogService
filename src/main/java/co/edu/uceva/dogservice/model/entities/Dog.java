package co.edu.uceva.dogservice.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;

    @NotEmpty(message = "La raza no puede estar vacía")
    @Size(min = 2, max = 50, message = "La raza debe tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String raza;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 25, message = "La edad no puede ser mayor a 25 años")
    @Column(nullable = false)
    private Integer edad;

    @NotNull(message = "El peso es obligatorio")
    @DecimalMin(value = "0.1", inclusive = true, message = "El peso debe ser mayor a 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "El peso no puede ser mayor a 100 kg")
    @Column(nullable = false)
    private Double peso;

    @NotEmpty(message = "El color no puede estar vacío")
    @Size(min = 2, max = 30, message = "El color debe tener entre 2 y 30 caracteres")
    @Column(nullable = false)
    private String color;

    @NotEmpty(message = "El sexo es obligatorio")
    @Pattern(regexp = "^(Macho|Hembra)$", message = "El sexo debe ser 'Macho' o 'Hembra'")
    @Column(nullable = false, length = 6)
    private String sexo;

    @NotNull(message = "El estado de vacunación es obligatorio")
    @Column(nullable = false)
    private Boolean vacunado;

    @NotNull(message = "El estado de esterilización es obligatorio")
    @Column(nullable = false)
    private Boolean esterilizado;
}
