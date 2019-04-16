package org.formacio.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;






@Component
public class ServeiConsultaEmpreses {

	// aquest servei necessita d'un integrador de cotitzacions per retornar la informacio
	// declarau la dependencia i feis que spring la resolgui
	
	
	@Autowired
	IntegradorCotitzacions cotizaciones ;
	

	float cotizacionDiaria=0f;
	
	@PostConstruct
	public void iniciar() {
		cotizacionDiaria= getCotizaciones().obteMitjanaDiariaCotitzacions(); 
	}
	
	public float getCotizacionDiaria() {
		return cotizacionDiaria;
	}

	
	public IntegradorCotitzacions getCotizaciones() {
		return cotizaciones;
	}

	
	public void setCotizaciones(IntegradorCotitzacions cotizaciones) {
		this.cotizaciones = cotizaciones;
	
	}

	

	// modifiqueu aquest metode per canviar el 0 per el valor obtingut del clientCotitzacionsWS
	public String infoEmpresa(String empresa) {
		return "La empresa " + empresa + " cotitza a " + getCotizaciones().obteCotitzacio(empresa);
	}

	
	// modifiqueu aquest metode per canviar el 0 per el valor obtingut del clientCotitzacionsWS
	// aquest es, segurament, l'exercici mes xungo !
	// com farieu per fer que el metode infoDiari de obteMitjanaDiariaCotitzacions nomes se crides una vegada?
	// pista (si nomes s'ha de cridar una vegada ... haurem de guardar el resultat a alguna banda ... )
	
	public String infoDiari() {
		return "La cotitzacio mitjana diaria es " + getCotizacionDiaria();
	}

}
