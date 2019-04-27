package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Agenda {
	
	//Variables
	@Autowired
	AgendaService agenda = new AgendaService();

	
	
	
	//getters y setters
	
	public AgendaService getAgenda() {
		return agenda;
	}
	
	//Lógica
	
	@RequestMapping(path="/nombre")
	@ResponseBody
	public int numeroPersonasAgenda() {
		return getAgenda().nombreContactes();
	}

}
