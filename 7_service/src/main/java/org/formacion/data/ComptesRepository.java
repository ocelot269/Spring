package org.formacion.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ComptesRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public void moviment(String client, int moviment) {
		
		Compte compte = em.find(Compte.class, client);
		compte.setQuantitat(compte.getQuantitat() + moviment);
		
		if (compte.getQuantitat() < 0) {
			throw new RuntimeException("compte negatiu: operacio no permesa");
		}
		
		em.merge(compte);
	}
	
	public String info (String client) {
		Compte compte = em.find(Compte.class, client);
		return compte.toString();
	}

}
