package com.behl.openapi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import com.behl.SpringConfigurationPropertiesApplication;
import com.behl.openapi.properties.OpenApiConfigurationProperties;

import io.swagger.v3.oas.models.OpenAPI;

class OpenApiConfigurationTest {

	private ApplicationContextRunner context;

	@BeforeEach
	void setUp() {
		context = new ApplicationContextRunner().withUserConfiguration(SpringConfigurationPropertiesApplication.class);
	}

	@Test
	@DisplayName("Assert OpenAPI bean creation")
	void openApiBeanCreationTest() {
		context.run(assertableContext -> assertThat(assertableContext).hasSingleBean(OpenAPI.class));
	}

	@Test
	@DisplayName("Assert OpenAPI bean creation")
	void openApiCorrectInformationPresentTest() {
		context.run(assertableContext -> {
			final var openApiBean = assertableContext.getBean(OpenAPI.class);
			final var openApiConfigurationProperties = assertableContext.getBean(OpenApiConfigurationProperties.class);

			assertThat(openApiBean.getInfo()).isNotNull();
			assertThat(openApiBean.getInfo().getTitle())
					.isEqualTo(openApiConfigurationProperties.getOpenApi().getTitle());
			assertThat(openApiBean.getInfo().getDescription())
					.isEqualTo(openApiConfigurationProperties.getOpenApi().getDescription());
			assertThat(openApiBean.getInfo().getVersion())
					.isEqualTo(openApiConfigurationProperties.getOpenApi().getApiVersion());
			assertThat(openApiBean.getInfo().getContact().getEmail())
					.isEqualTo(openApiConfigurationProperties.getOpenApi().getContact().getEmail());
			assertThat(openApiBean.getInfo().getContact().getName())
					.isEqualTo(openApiConfigurationProperties.getOpenApi().getContact().getName());
			assertThat(openApiBean.getInfo().getContact().getUrl())
					.isEqualTo(openApiConfigurationProperties.getOpenApi().getContact().getUrl());
			assertThat(openApiBean.getSecurity()).isNotNull();
			assertThat(openApiBean.getSecurity().size()).isEqualTo(1);
		});
	}

}
