package fes.aragon.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity(name="cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_clt;
    @NotEmpty(message = "Nombre no debe ser vacío")
    @Column(length = 30)
    private String nombre;
    @NotEmpty(message = "Paterno no debe ser vacío")
    @Column(name = "apellido",length = 30)
    private String apellidoPaterno;
    @Email(message = "Formato incorrecto de correo")
    @Column(name = "correo",length = 70)
    private String correo;
    @Column(length = 10)
    @Size(min = 10,max = 10,message = "Teléfono debe ser de 10 dígitos")
    @Pattern(regexp = "55[0-9]{8,8}",message = "Debe comenzar con 55 el teléfono")
    private String telefono;
}
