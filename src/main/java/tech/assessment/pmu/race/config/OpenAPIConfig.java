package tech.assessment.pmu.race.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Race Service",
                description = "Technical documentation of all REST API methods.",
                version = "v1"
        ),
        servers = {
                @Server(url = "/", description = "Default Server URL")
        }
)
public class OpenAPIConfig {
}
