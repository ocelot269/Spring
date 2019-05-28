package org.formacio.servei;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Aquesta classe NO s'ha de modificar
 */
@Service
public class FidalitzacioService {

	private List<String> emailsPremiats = new ArrayList<>();
	
	public void notificaRegal(String email) {
		emailsPremiats.add(email);
	}

	public List<String> getEmailsPremiats() {
		return emailsPremiats;
	}
	
	
}
