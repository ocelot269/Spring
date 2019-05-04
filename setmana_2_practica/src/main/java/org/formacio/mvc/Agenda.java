package org.formacio.mvc;

import org.formacio.repositori.AgendaService;
import org.formacio.repositori.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
public class Agenda{
	
	//Variables
	@Autowired
	AgendaService agenda;

	//Lógica
	
	@RequestMapping(path="/nombre")
	@ResponseBody
	public String numeroPersonasAgenda() {
		Integer numeroContactos = agenda.nombreContactes();
		return numeroContactos.toString();	
	}
	
	@RequestMapping(path="/telefon")
	@ResponseBody
	public String busquedaTelefonoID(@RequestParam String id) {
		return agenda.recupera(id).getTelefon();
	}
	
	

	@RequestMapping(path="/contacte/{id}")
	@ResponseBody
	public Persona encontrarPersona(@PathVariable String id)throws Exception {
		if (agenda.recupera(id)!=null) {
			return agenda.recupera(id);
		}
		else {
			throw new OperationException();
		}
		
	}
	
	
	@RequestMapping(path="/afegir" , method = RequestMethod.POST)
	@ResponseBody
	public String agregarPersona(@RequestParam String id, @RequestParam String nom, @RequestParam String telefon) {
		agenda.inserta(id, nom, telefon);
		return "ok";
	}
	
	
}
