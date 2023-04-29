package com.example.Mensajeria.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    Docket ap1() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API REST MENSAJERIA EXPRESS")
                .description("\uD83D\uDCEC\uD83D\uDCE6\uD83D\uDE9A Esta API permite registar clientes, empleados y gestionar envios de una empresa de mensajeria, cuenta con cuatro controladores "
                        + "\n" + "para cada función respectiva y permite realizar peticiones de acuerdo a dos roles de usuario (admin, user)\uD83D\uDD11\uD83E\uDDD1." + "\n" +
                        "Se recomienda leer el readme del proyecto para entender mejor su aplicación y uso \uD83D\uDCD6✅.")
                .version("1.0.0")
                .build();
    }
}

