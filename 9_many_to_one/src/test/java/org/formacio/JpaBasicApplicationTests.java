package org.formacio;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.formacio.domain.Municipi;
import org.formacio.domain.Persona;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements= {"insert into t_municipis (mun_id, mun_nom) values (1, 'Andratx')",
		          "insert into t_persones (per_id, per_nom, per_munid) values (1, 'Joan', 1)"})
public class JpaBasicApplicationTests {

	@PersistenceContext
	private EntityManager em;

	@Test
	public void test_operacions_many_to_one_carrega() { 
		
		Persona joan = em.find(Persona.class, 1L);
		
		Assert.assertEquals("Joan", joan.getNom());
		
		Municipi andratx = joan.getMunicipi();

		Assert.assertEquals("Andratx", andratx.getNom());

	}	
		

	@Test
	@Transactional
	public void test_operacions_many_to_one_insercio() { 
		
		Municipi selva = new Municipi();
		selva.setNom("Selva");
		
		Persona aina = new Persona();
		aina.setNom("Aina");
		aina.setMunicipi(selva);
		
        em.persist(selva);
        em.persist(aina);
        
        Assert.assertNotNull(selva.getId());
        Assert.assertNotNull(aina.getId());

	}	

}
