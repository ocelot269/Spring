package org.formacio.servei;

import java.util.Optional;

import javax.transaction.Transactional;

import org.formacio.domain.Factura;
import org.formacio.domain.LiniaFactura;
import org.formacio.repositori.FacturesRepositori;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class FacturesService {
	
	@Autowired
	FacturesRepositori repositorio;
	
	/*
	 * Aquest metode ha de carregar la factura amb id idFactura i afegir una nova linia amb les dades
	 * passades (producte i totalProducte)
	 * 
	 * S'ha de retornar la factura modificada
	 * 
	 * Per implementar aquest metode necessitareu una referencia (dependencia) a FacturesRepositori
	 */
	public Factura afegirProducte (long idFactura, String producte, int totalProducte) {
		LiniaFactura linea = new LiniaFactura();
		Optional<Factura> factura = repositorio.findById(idFactura);
		if (factura.isPresent()) {
			linea.setProducte(producte);
			linea.setTotal(totalProducte);
			factura.get().getLinies().add(linea);
			repositorio.save(factura.get());
		}
		return factura.get();
	}
}
