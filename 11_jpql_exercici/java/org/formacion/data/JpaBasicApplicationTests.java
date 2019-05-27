package org.formacion.data;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	@Autowired
	private MockMvc mockMvc;

	// Tant n'Aina com en Pau tenen 100 euros
	
	@Test
	public void test_peticio_web() throws Exception {
        this.mockMvc.perform(get("/moviment")
		        .param("origen", "Aina")
		        .param("desti", "Pau")
		        .param("quantitat", "150"))
    		        .andExpect(status().isOk())
        		        ;
	}

}
