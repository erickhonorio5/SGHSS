package gestao.sghss.configs;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.List;

@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class OpenApiConfig {

    private final Environment env;

    public OpenApiConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        String[] profiles = env.getActiveProfiles();
        String activeProfile = profiles.length > 0 ? profiles[0] : "default";

        Server server = new Server();
        server.setUrl(
                "prod".equalsIgnoreCase(activeProfile)
                        ? "https://sghss-api.com.br"
                        : "http://localhost:8080"
        );

        return new OpenAPI()
                .servers(List.of(server))
                .info(new Info()
                        .title("SISTEMA DE GESTÃO HOSPITALAR E DE SERVIÇOS DE SAÚDE (SGHSS)")
                        .description("Projeto Final de Curso - Análise e Desenvolvimento de Sistemas")
                        .version("v0.0.1")
                        .license(new License().name("All rights reserved").url(""))
                )
                .externalDocs(new ExternalDocumentation());
    }
}
