package com.ub.edu.bda;

import java.util.Set;

public class Tienda {

	private Integer id;
	private String nombre;
	private String direccion;
	private String telefono;
	
	//afegim l'objecte catalogo amb cardinalitat N
	private Set<Catalogo> catalogos;
	
	public Tienda(String nombre, String direccion, String telefono) {
		super();
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public Tienda() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
//GETTERS I SETTERS DE LA RELACIO N-N
	public Set<Catalogo> getCatalogos() {
		return catalogos;
	}

	public void setCatalogos(Set<Catalogo> catalogos) {
		this.catalogos = catalogos;
	}
	

}
