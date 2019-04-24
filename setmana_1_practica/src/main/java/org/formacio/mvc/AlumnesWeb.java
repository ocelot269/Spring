package org.formacio.mvc;


import org.formacio.component.ServeiAlumnat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AlumnesWeb {

	//Variables
@Autowired
private ServeiAlumnat alumnos;


//getters y setters
public ServeiAlumnat getAlumnos() {
	return alumnos;
}

@Autowired
private ServeiAlumnat serveiAlumnat;

@RequestMapping(path = "/alumnes")
@ResponseBody
public int nombreAlumnes() {
	return serveiAlumnat.nombreAlumnesRepositori();
}




}
