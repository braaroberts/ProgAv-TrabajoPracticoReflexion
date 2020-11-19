package com.ar.Utilidades;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class UBean {
	
	
	public static ArrayList<Field> obtenerAtributos(Object obj){
		
		ArrayList<Field> listaField = new ArrayList<Field>();
		
		Class c = obj.getClass();
		Field[] fields = c.getFields();
		for (Field field : fields) {
			listaField.add(field);
		}
		return listaField;
	
	}
	
	public static void ejecutarSet(Object o, String att, Object valor) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		Class c = o.getClass();
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			if( method.getName().equalsIgnoreCase("set"+ att)) {
				Object[] ob = new Object[1];
				ob[0]= valor;
				method.invoke(o, ob);
			}
		}
		
		
	}
	
	public static Object ejecutarGet(Object o, String att) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class c = o.getClass();
		Method[] methods = c.getMethods();
		for (Method method : methods) {
			if( method.getName().equalsIgnoreCase("get"+ att)) {
				return  method.invoke(o, null);
			}
		}
		return null;
	}
	
	
	
}
