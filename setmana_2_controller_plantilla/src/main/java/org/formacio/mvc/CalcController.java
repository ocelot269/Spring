package org.formacio.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Controlador web que ofereix funcionalitat per fer calculs
 * 
 * El @RequestMapping a nivell de classe fa que tots els paths especificats
 * al metodes amb @RequestMapping tenguin un path amb el prefixe "/cal"
 */
@Controller
@RequestMapping("/calc")
public class CalcController {

	/**
	 * Mapping senzill a /calc/info que retorna un String fitxe
	 * @ResponseBody fa que Spring MVC retorni al client el que retorna el metode
	 */
	@RequestMapping(path="/info")
	@ResponseBody
	public String info() {
		return "Calculadora online";
	}
	
	/**
	 * Metode amb parametres.  
	 */
	@RequestMapping(path="/suma")
	@ResponseBody
	public int suma (int a, int b) {
		return a + b;
	}
	
	/**
	 * Us d'un path variable: part del path emprat es mapeijat a una variable de metode
	 * p.e. /calc/seguent/5  fa que el valor del parametre original sigui 5
	 */
	@RequestMapping(path="/seguent/{original}")
	@ResponseBody
	public int seguent(@PathVariable int original) {
		return original +  1;
	}
	
	
	
}
