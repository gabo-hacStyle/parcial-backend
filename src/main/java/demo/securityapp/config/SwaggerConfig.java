package demo.securityapp.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Aplicacion web de Gestión de un parqueadero")
                        .version("1.0.0")
                        .description("Esta WebApp permite gestionar registros de un parqueadero, incluyendo creación, actualización y eliminación de registros." +
                                "Con el usuario admin contraseña 12345 entrar a la app. A /users/ para crear mas usuarios y poder usar la aplicacion")
                        .termsOfService("http://algo.com/terminos")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Gabriel Fajardo")
                                .email("u20231211209@usco.edu.co")
                                .url("http://algo.com"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Licencia MIT")
                                .url("http://opensource.org/licenses/MIT")));
    }
}