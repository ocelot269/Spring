package org.formacio;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.domain.Client;
import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.formacio.servei.FacturesService;
import org.formacio.servei.FidalitzacioService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest

@Sql(statements= {
		"delete from t_liniesfact","delete from t_factures","delete from t_clients",
		"insert into t_clients (cli_nom, cli_email) values ('joan', 'joan@email.com')",
		"insert into t_clients (cli_nom, cli_email) values ('aina', 'aina@email.com')",
		"insert into t_factures (fac_id, fac_client) values (1, 'joan')",
		"insert into t_factures (fac_id, fac_client) values (2, 'joan')",
		"insert into t_liniesfact (lin_id, lin_producte, lin_total, lin_factura) values (1,'tablet',300,1)",
		"insert into t_liniesfact (lin_id, lin_producte, lin_total, lin_factura) values (2,'sobrassada',2,2)",
		"insert into t_liniesfact (lin_id, lin_producte, lin_total, lin_factura) values (3,'formatge',4,2)"
        })
        
public class JpaBasicApplicationTests {

	@PersistenceContext 
	private EntityManager em;
	
	@Autowired(required=false)
	private FacturesRepositori repositori;
	
	@Autowired(required=false)
	private FacturesService servei;
	
	@Autowired
	private FidalitzacioService fidalitzacioService;
	
	/**
	 * Comenceu fent el mapping de les entitats LiniaFactura, Client i Factura
	 * Heu d'emprar les propietats que ja hi ha definides, no n'heu de definir de noves a les classes.
	 * Per els ids de LiniaFactura i Factura heu d'emprar un valor generat amb estrategia IDENTITY
	 */
	@Test
	public void test_mapping() {
		Assert.assertNotNull(em.find(LiniaFactura.class, 1L));
		Assert.assertNotNull(em.find(Client.class, "joan"));
		Assert.assertNotNull(em.find(Factura.class, 1L));
	}
	
	
	/**
	 * Modifica la classe FacturesRepositori per a que sigui un repositori Spring Data per l'entitat Factura
	 */
	@Test
	public void test_es_repositori() { 
		Assert.assertNotNull(repositori);
		Assert.assertTrue(repositori instanceof Repository);
	}	
	
	/**
	 * Implementa el metode totalClient de FacturesRepositori per a que retorni el total que s'ha gastat
	 * en totes les factures d'un client donat
	 * Per aixo, emprarem una funcio que no hem introduit:
	 *     la funcio sum () en un select retorna, com es d'esperar, la suma dels valors de totes les files de
	 *     la select. Per tant, heu de fer una select que retorni totes les files de les factures del client
	 *     indicat i, per cada una d'elles, sumar el valor de la propietat total
	 */
	@Test
	public void test_total_client() {
		Assert.assertEquals(306L, repositori.totalClient("joan"));
	}
	
	
	
	/**
	 * Si no ho has fet, fes qeu FacturesRepositori sigui un repositori amb els metodes CRUD automatics
	 */
	@Test
	public void test_repositori_es_crud() {
		Assert.assertTrue(repositori instanceof CrudRepository);
	}

	
	/**
	 * Implementa FacturesRepositori emprant la tecnica de queries derivades un metode que retorni
	 * totes les factures d'un client
	 * 
	 * Fet aixo, modifica el test per tal que factures se inicialitzi amb una invocacio al metode 
	 * que has creat
	 */
     @Test
     public void test_query_generada() {
    	      // canvia el null per una cridada a repositori.<metode_creat>("joan");
    	     List<Factura> facturesJoan = null; 
    	     
    	     Assert.assertNotNull(facturesJoan);
    	     Assert.assertEquals(2, facturesJoan.size());
    	     Assert.assertEquals("joan", facturesJoan.get(0).getClient().getNom());
     }
	
	/**
	 * Modifica FacturaService per a que sigui un component amb el rol de Service
	 */
	@Test
	public void test_factura_component() {
		Assert.assertNotNull(servei);
	}
	
	
	/**
	 * Modifiqueu el mapping de Factura per tal que totes les operacions de persistencia que
	 * es fan sobre Factura es propaguin (cascade) sobre LiniaFactura
	 */
	@Test
	@Transactional
	public void test_propaga_operacions_factura_a_linies() {
		Factura factura = em.find(Factura.class, 1L);
		LiniaFactura linia = new LiniaFactura();
		linia.setProducte("nou producte");
		linia.setTotal(10);
		factura.getLinies().add(linia);
		em.flush();
	}
	
	/**
	 * Implementa el metode afegirProducte de FacturaService
	 */
	@Test
	@Commit
	public void test_nova_factura() {
		Factura nova = servei.afegirProducte(2L, "Galletes", 1);
		
		Assert.assertNotNull(nova.getId());
		Assert.assertEquals(2L, nova.getId().longValue());
		
		Assert.assertEquals(307L, repositori.totalClient("joan"));
	}
		
	/**
	 * Modifiqueu el metode afegirProducte per tal que quan la nova linia afegida sigui la
	 * quarta o mes de la factura que modifiquem, se notifiqui al client que te un regal.
	 * 
	 * Per fer aquesta notificacio, FacturaService ha de tenir una dependencia a FidalitzacioService
	 * i invocar el metode notificaRegal passant l'email (IMPORTANT: es l'email, no el nom del client)
	 * del client de la factura
	 */
	@Test
	@Commit
	public void test_notifica_regal() {
	    Assert.assertTrue(fidalitzacioService.getEmailsPremiats().isEmpty());	
	    
		servei.afegirProducte(2L, "primer", 1);

	    Assert.assertTrue(fidalitzacioService.getEmailsPremiats().isEmpty());	
        
	    servei.afegirProducte(2L, "segon", 1);
	    Assert.assertFalse(fidalitzacioService.getEmailsPremiats().isEmpty());	

	    Assert.assertTrue(fidalitzacioService.getEmailsPremiats().contains("joan@email.com"));	
	}
}
