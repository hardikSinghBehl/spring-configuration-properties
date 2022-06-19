package com.behl.openapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfiguration {

	@Value("${open-api.title}")
	private String title;

	@Value("${open-api.api-version}")
	private String apiVersion;

	@Value("${open-api.description}")
	private String description;

	@Value("${open-api.contact.email}")
	private String contactEmail;

	@Value("${open-api.contact.name}")
	private String contactName;

	@Value("${open-api.contact.url}")
	private String contactUrl;

	@Value("${open-api.security.name}")
	private String securitySchemeName;

	@Value("${open-api.security.scheme}")
	private String securityScheme;

	@Value("${open-api.security.bearer-format}")
	private String securityBearerFormat;

	@Bean
	public OpenAPI customOpenAPIConfiguration() {

		final var info = new Info().title(title).version(apiVersion).description(description)
				.contact(new Contact().email(contactEmail).name(contactName).url(contactUrl));

		return new OpenAPI().info(info).addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
				.components(new Components().addSecuritySchemes(securitySchemeName,
						new SecurityScheme().name(securitySchemeName).type(SecurityScheme.Type.HTTP)
								.scheme(securityScheme).bearerFormat(securityBearerFormat)));
	}
}