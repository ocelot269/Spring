package org.formacio.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculadoraController {

	/**
	 * Aquest mètode rebra les peticions tractades en aquest Controller que acabin amb 
	 * un error del tipus ArithmeticException (o algun dels seus subtipus).
	 * 
	 * Una alternativa seria, en lloc de posar l'ArithmeticException com a paràmetre del mètode
	 * posar-la com a atribut del @ExceptionHandler :
	 *   @ExceptionHandler(ArithmeticException.class)
	 */
	@ExceptionHandler()
	@ResponseBody
	public String error(ArithmeticException e) {
		// declarar l'excepcio com a parametre ens permet, per exemple, accedir al missatge concret
		return "Hi ha hagut un error a la operació: " + e.getMessage();
	}

	/**
	 * Versió que empra una resposta ResponseEntity<Integer>
	 * El Integer que parametritza el ResponseEntity indica quin es el tipus de cos (body)
	 * de la resposta  (fitxau-vos amb el rol semblant que té el @ResponseBody)
	 * L'objecte ResponseEntity té distints mètodes per accedir i modificar tots els elements
	 * que formen la resposta HTTP
	 */
	@RequestMapping(path="/divEntity")
	public ResponseEntity<Integer> divisioEntity (int a, int b) {
		// en aquest exemple controlam programaticament si la resposta es 
		// una resposta d'error o una resposta correcte
		if (b == 0) {
			return new ResponseEntity<>(0, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(a / b, HttpStatus.OK);
	}

	
	@RequestMapping(path="/div")
	public int divisio (int a, int b) {
		return a / b;
	}
}
