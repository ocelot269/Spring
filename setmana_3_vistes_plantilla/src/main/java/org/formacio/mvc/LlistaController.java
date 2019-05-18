package org.formacio.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LlistaController {

	private List<String> valors = new ArrayList<>();
	
	@RequestMapping(path="/llista")
	public String estat (Model model) {
		
		model.addAttribute("llista", valors);

		return "llista";
	}
	
	@RequestMapping(path="/add", method=RequestMethod.POST)
	public String afegir(String nou) {
		valors.add(nou);
		
		/*
		 * Una resposta redirect: instrueix al client (navegador web) que ha de fer una 
		 * segona petició cap a llista
		 */
		return "redirect:/llista";
	}
	
}
