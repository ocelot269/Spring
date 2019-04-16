package org.formacio;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.formacio.component.RepositoriAlumnes;
import org.formacio.component.ServeiAlumnat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class Setmana1Tests {

	@Autowired(required=false)
	private RepositoriAlumnes repositori;

	@Autowired(required=false)
	private ServeiAlumnat servei;


    @Autowired
    private MockMvc mockMvc;

	/**
	 * Modifiqueu la classe RepositoriAlumnesMemoria per a que sigui un compoment detectat per Spring 
	 */
	@Test
	public void test_respositori_component() {
		assertNotNull("RepositoriAlumnes es un component", repositori);
	}

	/**
	 * Modifiqueu la classe ServeiAlumnat per a que sigui un compoment detectat per Spring 
	 */
	@Test
	public void test_servei_component() {
		assertNotNull("ServeiAlumnat es un component", servei);
	}

	/**
	 * Implementau el metode ServeiAlumnat.matricula(id, nom), tal com trobareu alla especificat
	 */
	@Test
	public void test_matricula() {
		
	    boolean insertaNull = servei.matricula(4, null);
	    assertFalse("Inserir null ha de retornar false", insertaNull);
	    
	    assertFalse("null no s'ha inserit",repositori.llistaAlumnes().contains(null));
	    
	    boolean insertaNoNull = servei.matricula(4, "Laura");
	    assertTrue("Na Laura se pot matricular",insertaNoNull);
	    assertEquals("Laura", repositori.obteAlumne(4));
	    		
	}
	
	/**
	 * Modifiqueu la classe ServeiAlumnat per a que, just crear-se, inserti dos alumnes al repositori:
	 * id = 1, nom = Antonia
	 * id = 2, nom = Joan
	 */
	@Test
	public void test_creacio_dades_inicials() {
		assertEquals("Antonia", repositori.obteAlumne(1));
		assertEquals("Joan", repositori.obteAlumne(2));
	}
	
	
    /**
     * Construiu dins del package org.formacio.mvc un controlador que retorni el número d'alumnes que
     * hi ha al repositori quan es faci la peticio /alumnes
     * 
     * El controlador ha de tenir una referencia a ServeiAlumnat (via dependencia)
     * El controlador NO pot tenir cap referencia a RepositoriAlumnes
     * 
     * pista: si el controlador ha de coneixer el numero d'alumnes i nomes pot emprar ServeiAlumnat ...
     *  ... pot ser ServeiAlumnat necessitara un altre metode.
     */
    @Test
    public void test_web_nombre_alumnes() throws Exception {
    	int nombreAlumnes = repositori.llistaAlumnes().size();
    	
        this.mockMvc.perform(get("/alumnes")).andExpect(status().isOk())
                .andExpect(content().string(equalTo(String.valueOf(nombreAlumnes))));
        
        repositori.altaAlumne(5, "Antoni");
 
        this.mockMvc.perform(get("/alumnes")).andExpect(status().isOk())
        .andExpect(content().string(equalTo(String.valueOf(nombreAlumnes + 1))));


    }
	

}
