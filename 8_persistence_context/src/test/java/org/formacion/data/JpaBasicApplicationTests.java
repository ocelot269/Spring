package org.formacion.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("classpath:/insert.sql")
public class JpaBasicApplicationTests {

	
	@Autowired
	private PersonaOpBasic ops;
	
	@Test
	public void test_modificacio_i_merge() {
		ops.modificacio_i_merge();
	}
	
	@Test
	public void test_modificacio_sense_merge() {
		ops.modificacio_sense_merge();
	}
	
	@Test
	public void testmerge_sense_modificacio() {
		ops.merge_sense_modificacio();
	}
	
}
