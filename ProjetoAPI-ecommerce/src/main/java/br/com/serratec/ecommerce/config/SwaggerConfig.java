package br.com.serratec.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.serratec.ecommerce"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo(){
		ApiInfo apiInfo = new ApiInfoBuilder()
		.title("Ecommerce API - Grupo 5")
		.description("Essa é uma API desenvolvida para atividade final de APIRestful")
		.license("Apache License Version 2.0")
		.licenseUrl("https://www.apache.org/license/LICENSE-2.0")
		.version("1.0.0")
		.contact(new Contact("EcommerceManeirão","www.ecommerce.serratec.com.br", "turma05@gmail.com"))
		.build();
		return apiInfo;
		}

}
