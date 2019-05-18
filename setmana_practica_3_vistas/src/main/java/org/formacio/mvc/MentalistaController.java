package org.formacio.mvc;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MentalistaController {

	// numero que han d'endenvinar
	private int numero;
	
	// llista de intents que ha fet l'usuari
	private Set<Integer> intents = new LinkedHashSet<>();
	
	// classe per la generacio de nombres aletoris (tot aixo no s'ha de tocar)
	private Random random = new Random();
	
	/**
	 * Aquest metode no l'heu de tocar. 
	 * 
	 * Es una novetat que introduim aqui ja que vos pot ser util!
	 * Aquest metode, amb @ModelAttribute fa que tots els metodes que se retornen 
	 * des d'aquest controller tenguin al model, amb el nom "intents" la llista d'intents.
	 * 
	 * Per tant, heu de saber que des del template joc.html, sempre tendreu disponible
	 * la variable ${intents} amb els intents que ha fet l'usuari
	 */
	@ModelAttribute(name="intents")
	public Set<Integer> informaIntents() {
		return intents;
	}

	// feis que aquest metode es cridi just creat el component
	public void inicia() {
		intents.clear();
		numero = random.nextInt(10) + 1;
	}

	// creau un metode mapejat a la peticio home ("/") i que retorni la vista "joc"
	
	
	// feis un metode queque atengui a "/reinici" i
	// invoqui al metode inicia() i llavors redireccioni a ("/")
	
	
	// completeu el metode:
	// - ha d'afegir l'intent a la llista a on guardem els intents
	// - ha de guardar un atribut al model que digui si l'intent de l'usuari es correcte o no
	@RequestMapping("/intent")
	public String intent (@RequestParam int intent) {
		return "joc";
	}

}
