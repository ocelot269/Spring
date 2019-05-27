package org.formacio;


import java.util.Arrays;
import java.util.List;


import org.formacio.domain.Persona;
import org.formacio.repositori.InformesCens;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(statements= {
		"delete from t_persones","delete from t_municipis","delete from t_illes",
		"insert into t_illes values ('Eivissa')",
		"insert into t_illes values ('Menorca')",
		"insert into t_municipis (mun_id, mun_nom, mun_illa) values (1, 'Sant Antoni','Eivissa')",
		"insert into t_municipis (mun_id, mun_nom, mun_illa) values (2, 'Eivissa','Eivissa')",
		"insert into t_municipis (mun_id, mun_nom, mun_illa) values (3, 'Alaior','Menorca')",
        "insert into t_persones (per_id, per_nom, per_edat, per_munid) values (1, 'Joan', 19, 1)",
        "insert into t_persones (per_id, per_nom, per_edat, per_munid) values (2, 'Antonia', 20, 2)",
        "insert into t_persones (per_id, per_nom, per_edat, per_munid) values (3, 'Joana', 15, 2)",
        "insert into t_persones (per_id, per_nom, per_edat) values (4, 'Francina', 30)"
        })
public class JpaBasicApplicationTests {

	@Autowired
	private InformesCens repositori;
	
	/**
	 * Per fer els seguents tests, haureu de implementar a la classe 
	 *    org.formacio.repositori.InformesCens
	 *    
	 * cada un dels metodes que se proven.
	 * 
	 * El comentari del que ha de fer cada metode ho trobareu a la propia classe
	 */

	@Test
	public void test_habitants_municipi() { 
		List<Persona> eivissencs = repositori.habitantsMunicipi("Eivissa");
		Assert.assertEquals(2, eivissencs.size());
		Assert.assertTrue(hiHaNom(eivissencs, "Antonia"));
		Assert.assertTrue(hiHaNom(eivissencs, "Joana"));
	}	

	@Test
	public void test_nombre_habitants_illa() {
		Assert.assertEquals(0, repositori.nombreHabitantsIlla("Menorca"));
		Assert.assertEquals(3, repositori.nombreHabitantsIlla("Eivissa"));
	}
	
	@Test
	public void test_nombre_habitants_menors_edat () {
		Assert.assertEquals(0, repositori.nombreHabitantsMenorsEdat("Sant Antoni"));
		Assert.assertEquals(1, repositori.nombreHabitantsMenorsEdat("Eivissa"));
		Assert.assertEquals(0, repositori.nombreHabitantsMenorsEdat("Alaior"));
		
	}
	
	@Test
	public void test_llista_persones_sense_municipi() {
		List<Persona> personesSenseMunicipi = repositori.llistaPersonesSenseMunicipi();
		Assert.assertEquals(1, personesSenseMunicipi.size());
		Assert.assertTrue(hiHaNom(personesSenseMunicipi, "Francina"));
	}
	
	@Test
	public void test_llista_noms_ordenats_edat() {
		List<String> noms = repositori.llistaNomsPersonesOrdenatPerEdat("Eivissa");
		List<String> esperats = Arrays.asList("Joana","Joan","Antonia");
		Assert.assertEquals(esperats.size(), noms.size());
		for (int i = 0; i< esperats.size(); i++) {
			Assert.assertEquals(esperats.get(i), noms.get(i));
		}
	}
	
	// Metode d'utilitat. No es un test
	
	private boolean hiHaNom(List<Persona> resultat, String cercada) {
		for (Persona each: resultat) {
			if (each.getNom().equals(cercada)) {
				return true;
			}
		}
		return false;
	}
}
