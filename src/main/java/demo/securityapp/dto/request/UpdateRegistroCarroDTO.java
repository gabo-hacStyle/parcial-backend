package demo.securityapp.dto.request;

import demo.securityapp.models.TypeCarEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRegistroCarroDTO {

    private String placa;
    private Long fechaEntrada;
    private Long fechaSalida;
    private String ubicacion;
    private TypeCarEntity typeCarEntity;
}
