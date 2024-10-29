package demo.securityapp.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parking_registros")
@Builder
@Schema(description = "Entidad que representa un registro de un vehículo en el parqueadero.")
public class ParkingRegistros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único del registro (llave primaria)", example = "1", required = true)
    private Long id;
    @Schema(description = "Placa del vehículo", example = "ABC123", required = true)
    private String placa;
    @Schema(description = "Fecha de entrada", example = "21", required = true)
    private Long fechaEntrada;

    @Schema(description = "Fecha de salida", example = "21", required = false)
    private Long fechaSalida;

    @Schema(description = "Ubicación del vehículo en el parqueadero", example = "A1", required = true)
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "id_tipo_carro")
    @Schema(description = "Tipo de carro", required = true)
    private TypeCarEntity typeCarEntity;



}
