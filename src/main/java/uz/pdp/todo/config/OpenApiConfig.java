package uz.pdp.todo.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "TODO API",
                description = "API for Todo management system",
                version = "1.0",
                contact = @Contact(
                        name = "Pdp academy",
                        email = "pdp@gamil.com",
                        url = "https://pdp.uz"
                )
        ),
        servers = {
                @Server(
                        description = "Local",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Prod",
                        url = "https://api.olmoscrm.uz"
                ),
                @Server(
                        description = "Dev",
                        url = "https://dev-server.uz"
                )
        },
        security = {
                @SecurityRequirement(name = "bearerAuth")
//                @SecurityRequirement(name = "basicAuth")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT scientification",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
//@SecurityScheme(
//        name = "basicAuth",
//        description = "basic scientification",
//        scheme = "basic",
//        type = SecuritySchemeType.HTTP,
//        in = SecuritySchemeIn.HEADER
//)
public class OpenApiConfig {


//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(new Components()
//                        .addSecuritySchemes("bearerAuth",
//                                new SecurityScheme()
//                                        .name("bearerAuth")
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")));
//    }
}

// Authorization : Bearer aslkjhksadhjlsadlhjkdsahjkladhlsjkdsahkjl
