package com.ar.Anotaciones;

public class Persona {
	public String nombre;
	public String apellido;
	public Number edad;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Number getEdad() {
		return edad;
	}
	public void setEdad(Number edad) {
		this.edad = edad;
	}
	
	
	@Override
	public String toString() {
	
		return this.nombre + " " + this.apellido + " " + this.edad;
	}
	
}
