package org.formacio.mvc;

import org.formacio.component.RepositoriAlumnesMemoria;
import org.formacio.component.ServeiAlumnat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AlumnesWeb {

	//Variables
@Autowired
private ServeiAlumnat alumnos;


//getters y setters
public ServeiAlumnat getAlumnos() {
	return alumnos;
}

public void setAlumnos(ServeiAlumnat alumnos) {
	this.alumnos = alumnos;
}	
			//No funciona 
@RequestMapping(path="/alumnes")
public int alumnos() {
	getAlumnos().contarAlumnos();
	return getAlumnos().getNumeroAlumnos();
}




}
