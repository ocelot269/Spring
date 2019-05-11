package org.formacio.mapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Punt d'inici de l'aplicacio.
 * En aquesta classe la peculiaritat (respecte al que solem tenir, es el contingut del
 * metode main.
 */
@SpringBootApplication
public class JpaBasicApplication {

	public static void main(String[] args) {
		
		/* En general, al nostre main nomes tindrem:
		 *     SpringApplication.run(JpaBasicApplication.class, args);
		 *     
		 * Pero aqui guardem una referencia del que retorna aquest metode (un ApplicationContext)
		 * per obtenir el nostre component Util i provar el metode alta
		 */
		ApplicationContext ctx = SpringApplication.run(JpaBasicApplication.class, args);
		
		Util util = ctx.getBean(Util.class);
		
		System.out.println("----- creant persona --------");
		util.alta("Aina", Sexe.DONA);
	}
}
