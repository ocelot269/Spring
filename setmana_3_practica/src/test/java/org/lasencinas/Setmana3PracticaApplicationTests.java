package org.lasencinas;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Setmana3PracticaApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test_obte_nombre_contactes() throws Exception {
		mockMvc.perform(get("/nombre"))
				.andExpect(status().isOk())
				.andExpect(content().string("3"));
	}


	@Test
	public void test_obte_telefon() throws Exception {
		mockMvc.perform(get("/telefon?id=lin"))
				.andExpect(status().isOk())
				.andExpect(content().string("971-555888"));
	}
	

	@Test
	public void test_contacte() throws Exception {
		mockMvc.perform(get("/contacte/lin").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}



	@Test
	public void test_contacte_xml() throws Exception {
		mockMvc.perform(get("/contacte/lin").accept(MediaType.APPLICATION_XML))
				.andExpect(status().isOk())
				.andExpect(content().xml("<persona><clau>lin</clau><nom>Lina</nom><telefon>971-555888</telefon></persona>"));
	}

	/**
	 * A la peticio POST /afegir
	 *  amb parametres id, nom i telefon
	 * se ha de crear una nova entrada al repositori 
	 * 
	 */
	@Test
	public void test_nou_contacte() throws Exception {
		mockMvc.perform(post("/topics")
				           .param("id", "")
				           .param("nom", "Josep")
				           .param("telefon", "971-555326")
				     )
				.andExpect(status().isOk());
		
		// comprova que s'ha inserit
		mockMvc.perform(get("/contacte/jos").accept(MediaType.APPLICATION_XML))
		.andExpect(status().isOk());
	}


	/**
	 * Modifiqueu el codi per a que /afegir nomes es pugui cridar per POST
	 * 
	 */
	@Test
	public void test_nou_contacte_get_falla() throws Exception {
		mockMvc.perform(get("/afegir")
				           .param("id", "jos")
				           .param("nom", "Josep")
				           .param("telefon", "971-555326")
				     )
				.andExpect(status().is4xxClientError());
	}

}
