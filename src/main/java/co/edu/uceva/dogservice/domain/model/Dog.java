package co.edu.uceva.dogservice.domain.model;

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

    @NotEmpty(message = "No puede estar vacío")
    @Size(min = 2, max = 20, message = "El tamaño tiene que estar entre 2 y 20")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres")
    private String descripcion;

    @Min(value = 0, message = "El precio no puede ser negativo")
    @NotNull(message = "El precio es obligatorio")
    @Column(nullable = false)
    private Double precio;

    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @Size(max = 100, message = "El nombre del archivo no puede exceder los 100 caracteres")
    @Pattern(
            regexp = "^[\\w\\s\\-\\.\\(\\)]+\\.(jpg|jpeg|png|gif|bmp|webp)$",
            message = "El nombre del archivo debe ser válido y tener una extensión permitida"
    )
    private String foto;

    @NotEmpty(message = "La raza no puede estar vacía")
    @Size(min = 2, max = 50, message = "La raza debe tener entre 2 y 50 caracteres")
    @Column(nullable = false)
    private String raza;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 25, message = "La edad no puede ser mayor a 25 años")
    @Column(nullable = false)
    private Integer edad;

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