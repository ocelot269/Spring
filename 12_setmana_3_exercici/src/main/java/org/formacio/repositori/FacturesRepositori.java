package org.formacio.repositori;

import java.util.Optional;

import org.formacio.domain.Factura;
import org.springframework.data.repository.CrudRepository;


public interface FacturesRepositori  extends CrudRepository<Factura, Long>{

	public Number totalClient(String client);
	
	
	
}
