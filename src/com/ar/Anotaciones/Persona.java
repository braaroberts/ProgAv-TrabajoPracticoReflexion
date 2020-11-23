package com.ar.Anotaciones;

@Table(nombre="Personas")
public class Persona {
	@Id
	public int id;
	@Column(nombre="nombre")
	public String nombre;
	@Column(nombre="apellido")
	public String apellido;
	@Column(nombre="edad")
	public Number edad;

	
	public int getId() {
		return id;
	}
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
