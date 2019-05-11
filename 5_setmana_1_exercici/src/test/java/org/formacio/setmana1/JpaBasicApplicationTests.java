package org.formacio.setmana1;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.setmana1.data.LlibreNoExisteixException;
import org.formacio.setmana1.data.LlibreOpsBasic;
import org.formacio.setmana1.domini.Llibre;
import org.formacio.setmana1.domini.Recomanacio;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql("classpath:/insert.sql")
public class JpaBasicApplicationTests {

	@Autowired(required=false)
	private LlibreOpsBasic operacions;

    @Autowired
    private MockMvc mockMvc;

	@PersistenceContext
	private EntityManager em;
	
	private final String ISBN = "123456789";
	
	/**
	 * Observa (sense tocar!) l'estructura de la taula per guardar localitats a:
	 * src/main/resources/schema.sql
	 * 
	 * Modifica la classe Llibre per tal que es correspongui a la estructura de la taula
	 */
	@Test
	public void test_mapping() {
		em.find(Llibre.class, ISBN);
	}
	
	/* ------------------------------
	 * Les modificacions que el test demana a partir d'aqui es refereixen (a no ser que 
	 * es digui d'altre manera) a la classe LlibresOpsBasic
	 */
	
	/**
	 * Modifica la classe LlibreOpsBasic per a que sigui localitzada com a Component per Spring
	 */
	@Test
	public void test_component() {
		Assert.assertNotNull(operacions);
	}
	

	// -- Aneu implementant cada metode de LlibreOpsBasic que s'executa en cada test
	
	@Test
	public void test_carrega_existent() throws LlibreNoExisteixException {
		Assert.assertNotNull(operacions.carrega(ISBN));
		Assert.assertEquals("Spring Boot in action", operacions.carrega(ISBN).getTitol());
	}


	/**
	 *  Aquest test comprova el cas especial de que quan el llibre no existeix es llanca una LlibreNoExisteixException 
	 */
	@Test(expected=LlibreNoExisteixException.class)
	public void test_carrega_no_exitent() throws LlibreNoExisteixException {
		operacions.carrega("xxxxx");
		Assert.fail("Hauria d'haver llançat una excepcio");
	}

	@Test
	public void test_alta() {
		operacions.alta("4444", "Joan Xarequet", 1000, Recomanacio.PRESCINDIBLE, "Aplicacions sense Boot");
		Llibre nou = em.find(Llibre.class, "4444");
		Assert.assertNotNull(nou);
		Assert.assertEquals(1000, nou.getPagines().intValue());
		Assert.assertEquals(Recomanacio.PRESCINDIBLE, nou.getRecomanacio());
	}
	
	@Test
	public void test_elimina() {
		Assert.assertNotNull(em.find(Llibre.class, ISBN));
		Assert.assertTrue(operacions.elimina(ISBN));
		Assert.assertNull(em.find(Llibre.class, ISBN));
	}
	
	@Test
	public void test_modifica() {
		Llibre original = em.find(Llibre.class, ISBN);
		original.setPagines(2000);
		operacions.modifica(original);
		Llibre modificat = em.find(Llibre.class, ISBN);
		Assert.assertEquals(2000, modificat.getPagines().intValue());
	}
	
	@Test
	public void test_existeix() {
		Assert.assertTrue(operacions.existeix(ISBN));
		Assert.assertFalse(operacions.existeix("No existeix"));
	}
	
	@Test
	public void test_recomanacio() {
		Assert.assertNull(operacions.recomenacioPer("No existeix"));
		Assert.assertEquals(Recomanacio.OBRA_MESTRE, operacions.recomenacioPer(ISBN));
	}
	
	/* ------------------------------
	 * Ara que tot vos funciona, completeu el codi de LlibreController (package org.formacio.setmana1.mvc)
	 */
	
	@Test
	public void test_peticio_web() throws Exception {
        this.mockMvc.perform(get("/recomanacio").param("isbn", ISBN)).andExpect(status().isOk())
            .andExpect(content().string(containsString(Recomanacio.OBRA_MESTRE.toString())));

	}

}
