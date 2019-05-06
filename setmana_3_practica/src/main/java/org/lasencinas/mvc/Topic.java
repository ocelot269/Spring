package org.lasencinas.mvc;

public class Topic {

	//Variables
	private String id = null;
	private String nombre = null;
	private String descripcion = null;
	
	//Constructor
	public Topic() {

	}
	
	
	//Constructor Sobrecargado
	public Topic(String id, String nombre, String descripcion) {
		super();
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);;
	}
	
	//getters y setters
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
