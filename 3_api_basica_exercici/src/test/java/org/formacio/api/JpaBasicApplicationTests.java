package org.formacio.api;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("classpath:/insert.sql")
public class JpaBasicApplicationTests {

	@Autowired(required=false)
	private LocalitatOpBasic operacions;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Observa (sense tocar!) l'estructura de la taula per guardar localitats a:
	 * src/main/resources/schema.sql
	 * 
	 * Modifica la classe Localitat per tal que es correspongui a la estructura de la taula
	 */
	@Test
	public void test_mapping() {
		em.find(Localitat.class, 1L);
	}
	
	/* ------------------------------
	 * Les modificacions que el test demana a partir d'aqui es refereixen (a no ser que 
	 * es digui d'altre manera) a la classe LocalitatOpBasic
	 */
	
	/**
	 * Modifica la classe LocalitatOpBasic per a que sigui localitzada com a Component per Spring
	 */
	@Test
	public void test_component() {
		Assert.assertNotNull(operacions);
	}
	
	/**
	 * Implementa el metode carrega 
	 */
	@Test
	public void test_load() {
		Localitat selva = operacions.carrega(1L);
        Assert.assertNotNull(selva);
        Assert.assertEquals("Selva", selva.getNom());
        Assert.assertEquals(1000, selva.getHabitants().intValue());
	}
	
	/**
	 * Implementa el metode alta per donar d'alta noves localitats
	 * Important: fitxau-vos que no passem l'id. L'id l'ha de generar la base de dades
	 */
	@Test
	public void test_alta() {
		operacions.alta("nova",2000);
		Localitat alta = operacions.carrega(2L);
		Assert.assertNotNull(alta);
		Assert.assertEquals("nova", alta.getNom());
		Assert.assertEquals(2000, alta.getHabitants().intValue());
	}
	
	/**
	 * Implementa el metode elimina per tal que elimini la localitat
	 * amb id passat com a parametre
	 */
	@Test
	public void test_elimina() {
		Assert.assertNotNull(operacions.carrega(1L));
		operacions.elimina(1L);
		Assert.assertNull(operacions.carrega(1L));
	}
	
	/**
	 * Modifica el metode elimina per a que no falli si l'id passat
	 * no existeix
	 */
	@Test
	public void test_elimina_si_existeix() {
		Assert.assertNotNull(operacions.carrega(1L));
		operacions.elimina(1L);
		Assert.assertNull(operacions.carrega(1L));
		operacions.elimina(1L);
	}
	
	/**
	 * Implementa el metode modifica per a que guardi les modificacions 
	 * de  l'objecte  passat com a parametre
	 */
	@Test
	public void test_modifica() {
		Localitat modificada = new Localitat();
		modificada.setId(1L);
		modificada.setNom("modificada");
		
		operacions.modifica(modificada);
		
		Assert.assertEquals("modificada", operacions.carrega(1L).getNom());
	}
}
