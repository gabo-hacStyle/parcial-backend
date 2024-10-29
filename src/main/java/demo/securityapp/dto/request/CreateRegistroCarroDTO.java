package demo.securityapp.dto.request;

import demo.securityapp.models.TypeCarEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRegistroCarroDTO {
    private String placa;
    private String fechaEntrada;
    private String fechaSalida;
    private String ubicacion;
    private TypeCarEntity typeCarEntity;
}
