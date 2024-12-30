package com.mgc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "API GESTION TURNOS MEDICOS",
                description = "Backend para la gestion de turnos medicos",
                version = "1.0.0"
        ),
        servers = @Server(
                        description = "DEV Server",
                        url="http://localhost:8080"
        )

)




public class SwaggerConfig {

}
