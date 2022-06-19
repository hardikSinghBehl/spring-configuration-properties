package com.behl.openapi;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.behl.openapi.properties.OpenApiConfigurationProperties;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;

@Configuration
@EnableConfigurationProperties(OpenApiConfigurationProperties.class)
@AllArgsConstructor
public class OpenApiConfiguration {

	private final OpenApiConfigurationProperties openApiConfigurationProperties;

	@Bean
	public OpenAPI customOpenAPIConfiguration() {
		final var openApiProperties = openApiConfigurationProperties.getOpenApi();
		final var security = openApiProperties.getSecurity();
		final var contact = openApiProperties.getContact();

		final var info = new Info().title(openApiProperties.getTitle()).version(openApiProperties.getApiVersion())
				.description(openApiProperties.getDescription())
				.contact(new Contact().email(contact.getEmail()).name(contact.getName()).url(contact.getUrl()));

		return new OpenAPI().info(info).addSecurityItem(new SecurityRequirement().addList(security.getName()))
				.components(new Components().addSecuritySchemes(security.getName(),
						new SecurityScheme().name(security.getName()).type(SecurityScheme.Type.HTTP)
								.scheme(security.getScheme()).bearerFormat(security.getBearerFormat())));
	}
}