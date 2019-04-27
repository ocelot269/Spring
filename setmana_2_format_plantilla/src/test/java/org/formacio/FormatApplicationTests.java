package org.formacio;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.formacio.mvc.Persona;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FormatApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    
    @Test
    public void test_get_json() throws Exception {
    	MvcResult result = 
    			   mockMvc.perform(get("/persona").accept(MediaType.APPLICATION_JSON))
    	          .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                  .andReturn();
    	String response = result.getResponse().getContentAsString();

    	System.out.println("resposta json : " + response);
    	
    	Persona persona = new ObjectMapper().readValue(response, Persona.class);
    	
    	assertEquals("Pere", persona.getNom());
    	assertEquals("Selva", persona.getMunicipi());
    }
    
    @Test
    public void test_get_xml() throws Exception {
    	MvcResult result = 
    			   mockMvc.perform(get("/persona").accept(MediaType.APPLICATION_XML))
    	          .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_XML))
                  .andReturn();
    	String response = result.getResponse().getContentAsString();
    	
    	System.out.println("resposta xml : " + response);
    	
    	Unmarshaller unmarshaller = JAXBContext.newInstance(Persona.class).createUnmarshaller();
    	Persona persona = (Persona) unmarshaller.unmarshal(new StringReader(response));
    	
    	assertEquals("Pere", persona.getNom());
    	assertEquals("Selva", persona.getMunicipi());
    }
    
}
