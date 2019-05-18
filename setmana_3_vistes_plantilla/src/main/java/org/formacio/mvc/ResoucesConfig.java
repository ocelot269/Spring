package org.formacio.mvc;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Una classe @Configuration és un tipus especific de @Component (i per tant es detecta per el component-scan)
 * Quan Spring troba aquesta classe durant el component-scan, invocarà tots els seus mètodes anotats 
 * amb @Bean i enregistrarà el seu resultat a l'application context
 */
@Configuration
public class ResoucesConfig  {

	/**
	 * El MessageSource que crea aquest mètode sera enregistrat al application context i per tant,
	 * estarà diponible al thymeleaf quan necessiti accedir a un missatge externalitzat
	 */
	@Bean
	public MessageSource messageSource() {
		
		/*
		 * Aquest messageSource recuperara els missatges de:
		 *    /src/main/resources/i18n/principal_ca.properties o
		 *    /src/main/resources/i18n/principal_es.properties 
		 * en funcio de si el client vol el text en català o espanyol respectivament
		 */
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("i18n/principal");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}