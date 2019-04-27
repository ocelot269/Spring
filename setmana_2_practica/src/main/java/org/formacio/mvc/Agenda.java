package org.formacio.mvc;

import javax.xml.bind.annotation.XmlRootElement;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


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
	
	
	
	@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Pagina no encontrada")
	@ExceptionHandler()
	public String error(Exception noExiseContacto) {
		return "Hi ha hagut un error a la operació: " + noExiseContacto.getMessage();
	}
	
	
	@RequestMapping(path="/afegir" , method = RequestMethod.POST)
	@ResponseBody
	public void agregarPersona(String id, String nom, String telefon) {
		
		getAgenda().inserta(id, nom, telefon);
	}
	
	
}
