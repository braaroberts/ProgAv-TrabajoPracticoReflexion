package com.ar.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.ar.Anotaciones.Persona;
import com.ar.Servicios.Consultas;
import com.ar.Utilidades.UBean;

public class Test_Reflexion {
	@Test
	void test1_validaUtilidades_obtenerAtributos() {
		Persona p = new Persona();
		 ArrayList<Field> fields = UBean.obtenerAtributos(p);
		assertEquals("apellido", fields.get(1).getName());
	}
	
	@Test
	void test2_validaUtilidades_ejecutarSet_string() {
		Persona p = new Persona();
		try {
			UBean.ejecutarSet(p, "apellido", "Roberts");
			assertEquals("Roberts",p.getApellido());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
	}
	
	@Test
	void test3_validaUtilidades_ejecutarSet_Number() {
		Persona p = new Persona();
		try {
			UBean.ejecutarSet(p, "edad", 26);
			assertEquals(26, p.getEdad());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
		
	}
	
	@Test
	void test4_validaUtilidades_ejecutarGet_Number() {
		Persona p = new Persona();
		try {
			UBean.ejecutarSet(p, "edad", 26);
			assertEquals("26", UBean.ejecutarGet(p, "edad").toString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
		
	}
	
	@Test
	void test5_validaUtilidades_ejecutarGet_String() {
		Persona p = new Persona();
		try {
			UBean.ejecutarSet(p, "apellido", "Roberts");
			assertEquals("Roberts", UBean.ejecutarGet(p, "apellido").toString());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			assertEquals(true, false);
			e.printStackTrace();
		}
		
	}
	
	@Test
	void test6_validaServicios_guardar() {
		Persona p = new Persona();
		p.nombre = "Brian";
		p.apellido = "Roberts";
		p.edad = 25;
		
		Consultas.guardar(p);
		
		
	}
	

	

}
