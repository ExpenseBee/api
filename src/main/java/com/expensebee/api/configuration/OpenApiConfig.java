package com.expensebee.api.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {
  private OpenAPI openAPI = new OpenAPI();
  @Bean
  public OpenAPI customOpenAPI() {
    var bearerAuth = "bearerAuth";

    return  openAPI
      .addSecurityItem(new SecurityRequirement().addList(bearerAuth))
      .components(customComponents(bearerAuth))
      .info(customInfo());
  }

  private Info customInfo() {
    var info = new Info();
    info.title("Expense Bee");
    info.version("0.0.1");
    return info;
  }

  private Components customComponents(String bearerAuth) {
    var component = new Components();
    var securityScheme = securityScheme();
    component.addSecuritySchemes(bearerAuth, securityScheme);
    return component;
  }

  private SecurityScheme securityScheme(){
    var securityScheme = new SecurityScheme();
    securityScheme.type(SecurityScheme.Type.HTTP);
    securityScheme.scheme("Bearer");
    securityScheme.bearerFormat("JWT");
    return securityScheme;
  }
}
