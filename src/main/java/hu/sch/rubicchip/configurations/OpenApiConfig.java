package hu.sch.rubicchip.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${openapi.info.title:My API}") String title,
            @Value("${openapi.info.version:0.0.1}") String version,
            @Value("${openapi.servers.url:http://localhost}") String serverUrl,
            @Value("${openapi.servers.description:Generated server url}") String serverDescription) {
        return new OpenAPI()
                .openapi("3.1.0")
                .info(new Info().title(title).version(version))
                .servers(List.of(new Server().url(serverUrl).description(serverDescription)));
    }
}
