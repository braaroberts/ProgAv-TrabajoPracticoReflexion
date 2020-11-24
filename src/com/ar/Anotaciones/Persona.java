package com.ar.Anotaciones;


@Table(nombre="Personas")
public class Persona {
	@Id
	public Integer id;
	@Column(nombre="nombre")
	public String nombre;
	@Column(nombre="apellido")
	public String apellido;
	@Column(nombre="edad")
	public Integer edad;

	
	public Integer getId() {
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
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	@Override
	public boolean equals(Object obj) {
		Persona  p = (Persona)obj;
		return p.getId().equals(this.id);
	}
	
	
	@Override
	public String toString() {
	
		return this.nombre + " " + this.apellido + " " + this.edad;
	}
	
}
