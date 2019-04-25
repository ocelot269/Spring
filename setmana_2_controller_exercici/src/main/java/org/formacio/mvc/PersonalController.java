package org.formacio.mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiDevice.Info;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path= "/personal")
public class PersonalController {

	// no toqueu la declaracio de baseDeDades ni el metode getBaseDeDades
	// 
	// als metodes que anau afegint, tracteu aquest array com si fos la base de dades
	//
	// per exemple, obtenir la persona amb id 1 sera fer baseDeDades.get(1), etc etc 
	
	private List<String> baseDeDades = new ArrayList<>(Arrays.asList("Joana","Antonia","Pere"));
	
	public List<String> getBaseDeDades() {
		return baseDeDades;
	}
	
	
	@RequestMapping(path= "/info") //Te dice el total de personas que hay en la base de datos
	@ResponseBody
	public String info() {
		return "Hi ha " + getBaseDeDades().size() + " persones" ;
	}
	
	
	@RequestMapping(path ="/consulta")
	@ResponseBody	//En este metodo no es necesario un parametro,de no darle un parametro el valor será 0
	public String consulta(@RequestParam(value="id",required=false, defaultValue ="0") int id) {
		return getBaseDeDades().get(id);
	}
	// Poseu a partir d'aqui els vostre metode
}
