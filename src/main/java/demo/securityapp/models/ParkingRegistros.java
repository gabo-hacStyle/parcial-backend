package demo.securityapp.models;


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
public class ParkingRegistros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String placa;
    private Long fechaEntrada;
    private Long fechaSalida;
    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "id_tipo_carro")
    private TypeCarEntity typeCarEntity;



}
