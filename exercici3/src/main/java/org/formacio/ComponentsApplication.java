package org.formacio;

import org.formacio.component.ServeiConsultaEmpreses;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ComponentsApplication {

	public static void main(String[] args) {
		
		ConfigurableApplicationContext ctx = SpringApplication.run(ComponentsApplication.class, args);

		ServeiConsultaEmpreses servei = ctx.getBean(ServeiConsultaEmpreses.class);
		
		System.out.println(servei.infoEmpresa("cervesses.sa"));
		System.out.println(servei.infoEmpresa("shandies.sa"));
		System.out.println(servei.infoDiari());
		
		
		ctx.close();
	}
}
