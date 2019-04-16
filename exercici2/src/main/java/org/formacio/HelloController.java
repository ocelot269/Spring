package org.formacio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping(path="/")
	@ResponseBody
	public String saluda() {
		return "Necessitam aprovar!";
	}
}
