package demo.securityapp.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_carro")
@Schema(description = "Entidad que representa un tipo de carro.")
public class TypeCarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_carro")
    @Schema(description = "ID único del tipo de carro (llave primaria)", example = "1", required = true)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "typeCarEntity")
    @Schema(description = "Registros de parqueadero asociados a este tipo de carro.")
    private List<ParkingRegistros> parkingRegistros;

}
