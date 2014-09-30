package com.ub.edu.bda;

import java.util.Set;

public class Catalogo {
private Integer id ;
private String nombre ;
private String descripcion ;

//afegim l'objecte Articulo amb cardinalitat N
private Set<Articulo> articulos;

//afegim l'objecte Catalogo amb cardinalitat N

private Set<Tienda> tiendas;



public Catalogo() {
	super();

}

public Catalogo(String nombre, String descripcion) {
	super();
	this.nombre = nombre;
	this.descripcion = descripcion;
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

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

//NOUS GETTERS I SETTERS

public Set<Articulo> getArticulos() {
	return articulos;
}

public void setArticulos(Set<Articulo> articulos) {
	this.articulos = articulos;
}

//NOUS GETTERS I SETTERS TIENDAS
public Set<Tienda> getTiendas() {
	return tiendas;
}

public void setTiendas(Set<Tienda> tiendas) {
	this.tiendas = tiendas;
}

}

