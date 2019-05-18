package org.formacion.service;

import javax.transaction.Transactional;

import org.formacion.data.ComptesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OperacionsService {

	@Autowired
	private ComptesRepository repositori;
	
	public void traspassa(String origen, String desti, int quantitat) {
	
		System.out.println("Primera operacio");
		repositori.moviment(desti, quantitat);

		System.out.println("Segona operacio");
		repositori.moviment(origen, -1 * quantitat);

	}
	
	public String info (String client) {
		return repositori.info(client);
	}
}
