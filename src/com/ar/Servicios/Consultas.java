package com.ar.Servicios;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import com.ar.Utilidades.UBean;

public class Consultas {
	
	public static void guardar( Object obj ) {
		 StringBuilder sb = new StringBuilder();
		 sb.append("INSERT INTO ");
		 sb.append(obj.getClass().getSimpleName());
		 sb.append(" VALUES( ");
		 ArrayList<Field> fields = UBean.obtenerAtributos(obj);
		
		 for (int i = 0; i < fields.size(); i++) {
			 try {
					sb.append(UBean.ejecutarGet(obj, fields.get(i).getName())+" ");
					if( i!=fields.size()-1 ) {
						sb.append(", ");
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
		 }
		 sb.append(");");
		 System.out.println(sb.toString());
	}
	
	public static void modificar( Object obj ) {
			
	}
	
	public static void eliminar( Object obj ) {
		
	}
	
	public static Object obtenerPorId(Class c, Object id) {
		return null;
	}
}
