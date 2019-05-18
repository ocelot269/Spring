package org.formacion.mvc;

import org.formacion.service.OperacionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ComptesController {

	@Autowired
	private OperacionsService service;
	
	@RequestMapping(path="/moviment")
	@ResponseBody
	public String traspassa(String origen, String desti, int quantitat) {
		

		try {
			service.traspassa(origen, desti, quantitat);
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Estat final");
			System.out.println(service.info(origen));
			System.out.println(service.info(desti));
		}
		
		return "ok";
	}
}
