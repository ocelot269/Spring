package org.formacio;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.formacio.mvc.PersonalController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllerTests {

	@Autowired(required=false)
	PersonalController controller;
	
    @Autowired
    private MockMvc mockMvc;

    /**
     * No faria falta dir-ho ... pero per si de cas ;-)
     *   PersonaController ha de ser un controlador
     */
    @Test
    public void test_controlador() {
    	assertNotNull("controller es null!", controller);
    }
    
    /**
     * Tots els metodes que s'anomenen a continuacio han d'anar a PersonaController
     * Totes les peticions comencena amb /personal. Aprofitau aixo per posar aquest prefixe
     * nomes una vegada al controler (es a dir, que no estigui a cada request mapping)
     * 
     * Consell: com sempre, anau un a un amb els metodes i provau sovint els test !
     */
    
    
    /**
     * La peticio /personal/info ha de retornar el text:
     *    hi ha x persones
     * on x es el nombre de persones dins de la "base de dades" de PersonaController
     */
    @Test
    public void test_info() throws Exception {
        this.mockMvc.perform(get("/personal/info")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hi ha " + controller.getBaseDeDades().size() + " persones")));
    }

    /**
     * La peticio /personal/consulta?id=x
     *  ha de retornar el nom de la persona que ocupa el lloc x a la "base de dades" de persona controller
     */
    @Test
    public void test_obtenir_amb_request_param() throws Exception {
        this.mockMvc.perform(get("/personal/consulta?id=0")).andExpect(status().isOk())
        .andExpect(content().string(containsString("Joana")));
    	
        this.mockMvc.perform(get("/personal/consulta?id=1")).andExpect(status().isOk())
        .andExpect(content().string(containsString("Antonia")));
    	
    }

    /**
     * Modificau el metode anterior per que el parametre id sigui opcional i, si no se passa, se retorni 
     * el valor de la persona amb id 0 (si se passa el parametre, funcional com abans)
     */
    @Test
    public void test_obtenir_amb_request_param_opcional() throws Exception {
        this.mockMvc.perform(get("/personal/consulta")).andExpect(status().isOk())
        .andExpect(content().string(containsString("Joana")));
    	
    }

    /**
     * La peticio /personal/persona/x
     *  ha de retornar el nom de la persona que ocupa el lloc x a la "base de dades" de persona controller.
     *  Fitxau-vos que ara el parametre ara es part de la url, no un parametre http
     */
    @Test
    public void test_obtenir_amb_path_variable() throws Exception {
        this.mockMvc.perform(get("/personal/persona/0")).andExpect(status().isOk())
        .andExpect(content().string(containsString("Joana")));
    	
        this.mockMvc.perform(get("/personal/persona/1")).andExpect(status().isOk())
        .andExpect(content().string(containsString("Antonia")));
    	
    }
    
    
    /**
     * S'han de poder donar d'alta persones emprant (nomes) el metode POST a la url
     *    /personal/afegir
     * El parametre post necessari es "nom" amb el nom de la persona
     * La peticio ha de retornar el text "ok"
     */
    @Test
    public void test_afegir() throws Exception {

        this.mockMvc.perform(get("/personal/afegir?nom=error").param("nom", "Jacinto")).andExpect(status().is4xxClientError());

    	this.mockMvc.perform(post("/personal/afegir").param("nom", "Jacinto")).andExpect(status().isOk())
        .andExpect(content().string(containsString("ok")));
    	
        this.mockMvc.perform(get("/personal/persona/3")).andExpect(status().isOk())
        .andExpect(content().string(containsString("Jacinto")));
    	
    }
    

}
