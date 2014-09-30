package com.ub.edu.bda;

public class Articulo {
private Integer id;
private String descripcion;
private double precio;

// afegim l'objecte cataleg en cardinalitat 1
private Catalogo catalogo;


public Articulo() {
	super();
	// TODO Auto-generated constructor stub
}

public Articulo(Integer id, String descripcion,
		double precio) {
	super();
	this.id = id;
	this.descripcion = descripcion;
	this.precio = precio;
}



public Articulo(String descripcion, double precio) {
	super();
	this.descripcion = descripcion;
	this.precio = precio;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public double getPrecio() {
	return precio;
}

public void setPrecio(double precio) {
	this.precio = precio;
}
//NOUS GETTERS I SETTERS
public Catalogo getCatalogo() {
	return catalogo;
}

public void setCatalogo(Catalogo catalogo) {
	this.catalogo = catalogo;
}




}
