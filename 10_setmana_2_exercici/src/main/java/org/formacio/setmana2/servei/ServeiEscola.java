package org.formacio.setmana2.servei;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.formacio.setmana2.domini.Matricula;
import org.formacio.setmana2.repositori.EdatIncorrecteException;
import org.formacio.setmana2.repositori.RepositoriEscola;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional(rollbackOn = EdatIncorrecteException.class)
public class ServeiEscola {

	@Autowired
	RepositoriEscola repositori;

	/**
	 * Important: els alumnes i els cursos indicats JA existeixen a la base de dades.
	 * Per tant, els hem de carregar, no crear de nou.
	 * L'excepcio EdatIncorrecteException no s'ha de capturar. S'ha de propagar cap el client
	 */
	public List<Matricula> apunta (String curs, List<String> alumnes) throws EdatIncorrecteException {
		List<Matricula> matriculasAlumnos= new ArrayList<Matricula>();
		for (String alumno : alumnes) {
			matriculasAlumnos.add(repositori.apunta(alumno, curs));
		}
		return matriculasAlumnos;
}}
