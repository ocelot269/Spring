package org.lasencinas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloControler {
	@RequestMapping(path="/saludar")
	@ResponseBody
	public String saluda() {
		return "hola gente";
	}
}
