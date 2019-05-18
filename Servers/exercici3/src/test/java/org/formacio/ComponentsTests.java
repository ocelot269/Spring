package org.formacio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.formacio.component.ClientCotitzacionsWS;
import org.formacio.component.IntegradorCotitzacions;
import org.formacio.component.ServeiConsultaEmpreses;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentsTests {

	@Autowired(required=false)
	private IntegradorCotitzacions clientWS;

	@Autowired(required=false)
	private ServeiConsultaEmpreses servei;
	
	/**
	 * Modifiqueu la classe IntegradorCotitzacions per a que sigui un compoment detectat per Spring 
	 */
	@Test
	public void test_clientWS_component() {
		assertNotNull("ClientCotitzacionsWS no es un component ",clientWS);
	}
	
	/**
	 * ServeiConsultaEmpreses la classe IntegradorCotitzacions per a que sigui un compoment detectat per Spring 
	 */
	@Test
	public void test_serveiConsultaEmpreses_component() {
		assertNotNull("ServeiConsultaEmpreses no es un component ",servei);
	}

	/**
	 * Modifiqueu ServeiConsultaEmpreses per a que, en lloc de retornar sempre 0, empri els metodes de IntegradorCotitzacions
	 */
	@Test
	public void test_consulta_cotitzacio_empresa() {
		assertNotNull("ServeiConsultaEmpreses no es un component ",servei);
		assertEquals("consulta cotitzacio", "La empresa cervesses.sa cotitza a 30.0", servei.infoEmpresa("cervesses.sa"));
		assertEquals("consulta cotitzacio", "La empresa shandies.sa cotitza a 10.0", servei.infoEmpresa("shandies.sa"));
	}
	
	/**
	 * Per aquest exercici suposarem dues coses:
	 *   - l'aplicacio es llança cada dia, per tant la consulta a infoDiari sempre sera la mateixa durant la vida de l'execucio
	 *   - consultar obteMitjanaDiariaCotitzacions() es mooolt lent, i no volem fer-ho a cada invocacio de infoDiari(), ja qeu
	 *      hem dit que sempre retornara el mateix
	 * Modifiqueu la classe  ServeiConsultaEmpreses per tal que pugui mostrar la informacio de obteMitjanaDiariaCotitzacions
	 * pero fent la consulta UNA SOLA VEGADA.     
	 */
	@Test
	public void test_infoDiari() {
		assertNotNull("ServeiConsultaEmpreses no es un component ",servei);
		assertEquals("consulta info diari", "La cotitzacio mitjana diaria es 20.0", servei.infoDiari());
		servei.infoDiari();
		ClientCotitzacionsWS client  = (ClientCotitzacionsWS) clientWS;
		assertEquals("Nomes se ha invocat obteMitjanaDiariaCotitzacions() una vegada",1,client.nombreInvocacions);
	}
}
