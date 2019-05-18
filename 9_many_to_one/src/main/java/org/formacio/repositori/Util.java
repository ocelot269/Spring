package org.formacio.repositori;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class Util {

	@PersistenceContext
	private EntityManager em;
}
