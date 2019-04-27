package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(path="/telefon")
	@ResponseBody
	public String busquedaTelefonoID(String id) {
		return getAgenda().recupera(id).getTelefon();
	}
	
	
	@RequestMapping(path="/contacte/{id}")
	@ResponseBody
	public Persona encontrarPersona(@PathVariable String id)throws Exception {
		Exception noExisteContacto = new Exception();
		if (getAgenda().recupera(id)!=null) {
			return getAgenda().recupera(id);
		}
		else {
			throw noExisteContacto;
		}
		
	}
}
