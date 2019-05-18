package org.formacio.setmana2;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.setmana2.domini.Alumne;
import org.formacio.setmana2.domini.Curs;
import org.formacio.setmana2.domini.Matricula;
import org.formacio.setmana2.repositori.EdatIncorrecteException;
import org.formacio.setmana2.repositori.RepositoriEscola;
import org.formacio.setmana2.servei.ServeiEscola;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements= {
		"delete from t_matricules",
		"delete from t_alumnes",
		"delete from t_cursos",
		"insert into t_alumnes (alu_nom, alu_edat) values ('joan', 15)",
		"insert into t_alumnes (alu_nom, alu_edat) values ('aina', 18)",
		"insert into t_cursos (cur_nom, cur_edatminima) values ('spring', 16)",
		"insert into t_cursos (cur_nom, cur_edatminima) values ('surf', 13)",
		"insert into t_matricules (mat_id, mat_alumne, mat_curs) values (1,'joan','surf')",
		
})
public class JpaBasicApplicationTests {

	@Autowired(required=false)
	private RepositoriEscola repositori;

    @Autowired(required=false)
    private ServeiEscola servei;
	
	@PersistenceContext
	private EntityManager em;
	
	//------------------------------------------------
	// Tests sobre els mappings
	//
	// Observeu l'esquema de base de dades que espera l'aplicacio al fitxer
	//     src/main/resources/schema.sql
	
	// Comenceu fent el mapping per la classe Aliumne
	@Test
	public void test_mapping_alumne() {
		Alumne joan = em.find(Alumne.class, "joan");
		Assert.assertNotNull(joan);
		Assert.assertEquals("joan", joan.getNom());
		Assert.assertEquals(15, joan.getEdat());
	}
	
	// Ara la classe curs
	@Test
	public void test_mapping_curs() {
		Curs spring = em.find(Curs.class, "spring");
		Assert.assertNotNull(spring);
		Assert.assertEquals("spring", spring.getNom());
		Assert.assertEquals(16, spring.getEdatMinima());
	}
	
	// I finalment, la classe matricula
	@Test 
	public void test_mapping_matricula() {
		Matricula matricula = em.find(Matricula.class, 1L);
        Assert.assertNotNull(matricula);
        Assert.assertEquals("joan", matricula.getAlumne().getNom());
        Assert.assertEquals("surf", matricula.getCurs().getNom());
	}

	// Aquest test comprova que l'id de matricula sigui autogenerat. 
	// Si ja ho heu fet al punt anterior, aqui no heu de fer res
	@Test
	@Transactional
	public void test_matricula_id_generat() {
		Matricula matricula = new Matricula();
		matricula.setAlumne(em.find(Alumne.class, "joan"));
		matricula.setCurs(em.find(Curs.class, "surf"));
		em.persist(matricula);
	}

	// Implementeu, a la classe Alumne els metodes equals i hashCode
	// Aquests metodes han d'emprar nomes la propietat nom
	@Test
	public void test_equals_alumne() {
		Alumne a1 = new Alumne();
		a1.setNom("nom");
		Alumne a2 = new Alumne();
		a2.setNom(a1.getNom());
		a2.setEdat(20);
		Assert.assertEquals(a1, a2);
	}
	
	/**
	 * Aquest test comprova que hagueu fet una implementacio d'equals que 
	 * accepti subtipus. Si el test falla, esborreu la implementacio d'equals que
	 * teniu i torneu-la a generar ... pero fitxau-vos amb el que vos falta !!
	 */
	@Test
	public void test_equals_alumne_subtipus() {
		Alumne a1 = new Alumne();
		a1.setNom("nom");
		
		Alumne a3 = new Alumne() {};
		a3.setNom(a1.getNom());
		Assert.assertEquals(a1, a3);
	}
	
	//------------------------------------------------
	// Tests sobre el repositori
	
	/**
	 * Test que comprova que repositoriEscola es un component 
	 */
	@Test
	public void test_repositori_es_component() {
		Assert.assertNotNull(repositori);
	}

	/**
	 * Facil facil: implementeu el metode carregaCurs del repositori (no te truco)
	 */
	@Test
	public void test_carrega_curs() {
		Assert.assertNotNull(repositori);
		Curs spring = repositori.carregaCurs("spring");
		Assert.assertNotNull(spring);
		Assert.assertEquals("spring", spring.getNom());
		Assert.assertEquals(16, spring.getEdatMinima());
	}

	/**
     * Implementau el metode apunta
     * Teniu en compte que tant l'alumne indicat com el curs ja existeixen (no s'han de crear)
     * El metode ha de retornar la matricula recent creada
	 */
	@Test
	@Transactional
	public void test_apunta_ok() throws EdatIncorrecteException {
		Assert.assertNotNull(repositori);
		Matricula nova = repositori.apunta("aina", "spring");
		Assert.assertNotNull(nova);
		Assert.assertNotNull(nova.getId());
		Assert.assertEquals("aina", nova.getAlumne().getNom());
		Assert.assertEquals("spring", nova.getCurs().getNom());
	}

	/**
	 * Modifiqueu el metode anterior per fer que es llanci un error EdatIncorrecteException
	 * quan l'edat de l'alumne es menor a l'edat que requereix el curs
	 */
	@Test(expected=EdatIncorrecteException.class)
	@Transactional
	public void test_apunta_edat_incorrecte() throws EdatIncorrecteException {
		Assert.assertNotNull(repositori);
		repositori.apunta("joan", "spring");
		Assert.fail("En Joan no s'ha de poder apuntar");
	}
	
	//------------------------------------------------
	// Tests sobre el servei
	
	/**
	 * Primer facil: ServeiEscola ha de ser un component
	 */
	@Test
	public void test_servei_es_component() {
		Assert.assertNotNull(servei);
	}
	
	/**
	 * Implementeu el metode apunta del servei
	 * Aquest metode ha de delegar la tasca d'apuntar cada un dels alumnes al metode de
	 * repositori que ja teniu fet.
	 */
	@Test
	public void test_multiples_alumnes_ok() throws EdatIncorrecteException {
		Assert.assertNotNull(servei);
		List<Matricula> matricules = servei.apunta("surf", Arrays.asList("aina","joan"));
		
		Assert.assertEquals(2, matricules.size());
		for (Matricula matricula: matricules) {
			Assert.assertNotNull(matricula.getId());
		}
	}
	
	/**
	 * En aquest cas, el que heu de fer es canviar el servei per tal que quan 
	 * es produeix un EdatIncorrecteException NO es faci commit
	 * No heu de capturar l'error. L'error ha de seguir essent el resultat d'executar el
	 * metode. Tampoc heu de canviar la classe EdatIncorrecteException.
	 * Heu de canviar el comportament transaccional per tal que es faci un rollback
	 * quan es produeix aquest error concret
	 */
	@Test
	public void test_multiples_alumnes_error() {
		Assert.assertNotNull(servei);
		try {
			 servei.apunta("spring", Arrays.asList("aina","joan"));
			 Assert.fail("No ha procat error!");
		} catch (EdatIncorrecteException e) {}
		
		int matriculesSpring = em.createQuery("select mat from Matricula mat where mat.curs.nom = 'spring'")
				                  .getResultList()
				                  .size();
		
		Assert.assertEquals(0, matriculesSpring);
	}
}
