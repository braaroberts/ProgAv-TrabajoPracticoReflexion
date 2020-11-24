package com.ar.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.ar.Anotaciones.Persona;
import com.ar.Servicios.Consultas;
import com.ar.Utilidades.UBean;

public class Test_Reflexion {
	@Test
	void test1_Utils_obtenerAtributos() {
		Persona p = new Persona();
		 ArrayList<Field> fields = UBean.obtenerAtributos(p);
		assertEquals("nombre", fields.get(1).getName());
	}
	
	@Test
	void test2_Utils_ejecutarSet_string() {
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
	void test3_Utils_ejecutarSet_Number() {
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
	void test4_Utils_ejecutarGet_Number() {
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
	void test5_Utils_ejecutarGet_String() {
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
	void test6_Services_guardar() {
		Persona p = new Persona();
		p.nombre = "Brian";
		p.apellido = "Roberts";
		p.edad = 25;
		
		Consultas.guardar(p);
		
		
	}

	@Test
	void test7_Services_eliminar() {
		Persona p = new Persona();
		p.nombre = "brian";
		p.apellido = "roberts";
		p.edad = 25;
		p.id =1;
		
		p= (Persona) Consultas.guardar(p);
		Consultas.eliminar(p);
		assertTrue(Consultas.obtenerPorId(Persona.class,p.getId())==null);
	}
	
	@Test
	void test8_Services_Update() {
		Persona p = new Persona();
		p.nombre = "brian";
		p.apellido = "roberts";
		p.edad = 25;
		p.id =5;

		p.apellido = "ROBERTS";
		Consultas.modificar(p);
		
		Persona pModificada = (Persona) Consultas.obtenerPorId(Persona.class,p.getId());
		assertEquals("ROBERTS", pModificada.getApellido());
		
	}
	

		
	@Test
	void test09_Services_obtenerTodos() {
		Persona p = new Persona();
		p.nombre = "brIAn alFredo";
		p.apellido = "roBerts";
		p.edad = 25;
		p.id =2777;
		
	    ArrayList<Object> asd = Consultas.obtenerTodos(Persona.class);
	    assertTrue(asd.size()>0);
		
			
	}

	

}
