package demo.securityapp.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "roles")
@Schema(description = "Entidad que representa un rol en el sistema.")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Schema(description = "ID único del rol (llave primaria)", example = "1", required = true)
    private Long id;
    @Schema(description = "Nombre del rol", example = "ADMIN", required = true)
    private String name;

}
