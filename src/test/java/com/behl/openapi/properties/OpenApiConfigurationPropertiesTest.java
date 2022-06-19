package com.behl.openapi.properties;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 
 * @author hardikSinghBehl
 * 
 *         Fetches value(s) from .properties file in src/test/resources and
 *         validates accordingly
 *
 */
@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = OpenApiConfigurationProperties.class)
@TestPropertySource(locations = "classpath:application.properties")
class OpenApiConfigurationPropertiesTest {

	@Autowired
	private OpenApiConfigurationProperties openApiConfigurationProperties;

	@Test
	@DisplayName("Test to assert that values are being mapped to the POJO up from the .properties file")
	void configurationPropertiesPojoMappingTest() {
		assertThat(openApiConfigurationProperties.getOpenApi()).isNotNull();
		assertThat(openApiConfigurationProperties.getOpenApi().getContact()).isNotNull();
		assertThat(openApiConfigurationProperties.getOpenApi().getSecurity()).isNotNull();

		assertThat(openApiConfigurationProperties.getOpenApi().getTitle()).isNotNull();
		assertThat(openApiConfigurationProperties.getOpenApi().getDescription()).isNotNull();
		assertThat(openApiConfigurationProperties.getOpenApi().getApiVersion()).isNotNull();

		assertThat(openApiConfigurationProperties.getOpenApi().getContact().getEmail()).isNotNull();
		assertThat(openApiConfigurationProperties.getOpenApi().getContact().getName()).isNotNull();
		assertThat(openApiConfigurationProperties.getOpenApi().getContact().getUrl()).isNotNull();

		assertThat(openApiConfigurationProperties.getOpenApi().getSecurity().getName()).isNotNull();
		assertThat(openApiConfigurationProperties.getOpenApi().getSecurity().getScheme()).isNotNull();
		assertThat(openApiConfigurationProperties.getOpenApi().getSecurity().getBearerFormat()).isNotNull();
	}

	@Test
	@DisplayName("Test to assert that correct values are present in the configurationProperties POJO fields")
	void configurationPropertiesValueTest() {
		assertThat(openApiConfigurationProperties.getOpenApi().getTitle()).isEqualTo("Spring Configuration Properties");
		assertThat(openApiConfigurationProperties.getOpenApi().getDescription())
				.isEqualTo("POC to demonstrate using @ConfigurationProperties vs @Value");
		assertThat(openApiConfigurationProperties.getOpenApi().getApiVersion()).isEqualTo("1.0.0");

		assertThat(openApiConfigurationProperties.getOpenApi().getContact().getEmail())
				.isEqualTo("behl.hardiksingh@gmail.com");
		assertThat(openApiConfigurationProperties.getOpenApi().getContact().getName()).isEqualTo("Hardik Singh Behl");
		assertThat(openApiConfigurationProperties.getOpenApi().getContact().getUrl())
				.isEqualTo("https://www.linkedin.com/in/hardikSinghBehl");

		assertThat(openApiConfigurationProperties.getOpenApi().getSecurity().getName())
				.isEqualTo("AuthenticationBearer");
		assertThat(openApiConfigurationProperties.getOpenApi().getSecurity().getScheme()).isEqualTo("bearer");
		assertThat(openApiConfigurationProperties.getOpenApi().getSecurity().getBearerFormat()).isEqualTo("JWT");
	}

}
