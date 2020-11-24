package com.ar.Servicios;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ar.Anotaciones.Column;
import com.ar.Anotaciones.Id;
import com.ar.Anotaciones.Table;
import com.ar.Utilidades.UBean;
import com.ar.Utilidades.UConexion;

public class Consultas {
	

	public static Object guardar( Object obj ) {
		
		 ArrayList<Field> fields = UBean.obtenerAtributos(obj);
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("INSERT INTO ");
		 sb.append(obj.getClass().getAnnotation(Table.class).nombre());
		 sb.append("( ");
		
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				  Annotation[] anotaciones = fields.get(i).getAnnotations();
				  Boolean soloColumna = true;
				  for (Annotation annotation : anotaciones) {
					  if( annotation.annotationType().equals(Id.class) ) {
						  soloColumna = false;
					  }
				  }
				  if( anotaciones.length>0 && soloColumna ) {
					  
		 			sb.append("`");
		 			sb.append(fields.get(i).getName());
		 			
		 			if( i!=fields.size()-1 ) {
						sb.append("`, ");
					}else {
						sb.append("`)");
					}
				  }
				 	
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
	
		 sb.append(" VALUES( ");
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				  Annotation[] anotaciones = fields.get(i).getAnnotations();
				  Boolean soloColumna = true;
				  for (Annotation annotation : anotaciones) {
					  if( annotation.annotationType().equals(Id.class) ) {
						  soloColumna = false;
					  }
				  }
				  if( anotaciones.length>0 && soloColumna ) {
					  if( fields.get(i).getType().equals(String.class) ) {
				 			sb.append("'");
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName()));
				 			sb.append("'");
				 		}else {
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName())+" ");
				 		}
						if( i!=fields.size()-1 ) {
							sb.append(", ");
						}
				  }
				 	
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 sb.append(");");
		
		try {
			 UConexion uconexion = UConexion.getInstance();
			 PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			 ps.execute();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return Consultas.ObtenerUltimoRegistroAgregado(obj.getClass());
	}

	
	public static void eliminar( Object obj ) {
		 ArrayList<Field> fields = UBean.obtenerAtributos(obj);

		 StringBuilder sb = new StringBuilder();
		 sb.append("DELETE FROM ");
		 sb.append(obj.getClass().getAnnotation(Table.class).nombre());
		 sb.append(" WHERE ");
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				 	Id id = fields.get(i).getAnnotation(Id.class);
				 	if(id!=null) {
				 		sb.append(fields.get(i).getName());
				 		sb.append("=");
			 			if( fields.get(i).getType().equals(String.class) ) {
				 			sb.append("'");
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName()));
				 			sb.append("'");
				 		}else {
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName())+" ");
				 		}
				 	}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 sb.append(";");
		try {
			 UConexion uconexion = UConexion.getInstance();
			 PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			 ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void modificar( Object obj ) {
		 ArrayList<Field> fields = UBean.obtenerAtributos(obj);
		 StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE ");
		 sb.append(obj.getClass().getAnnotation(Table.class).nombre());
		 sb.append(" SET ");
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				  Annotation[] anotaciones = fields.get(i).getAnnotations();
				  Boolean soloColumna = true;
				  for (Annotation annotation : anotaciones) {
					  if( annotation.annotationType().equals(Id.class) ) {
						  soloColumna = false;
					  }
				  }
				  if( anotaciones.length>0 && soloColumna ) {
					  sb.append(fields.get(i).getName());
				 		sb.append("=");
					  if( fields.get(i).getType().equals(String.class) ) {
				 			sb.append("'");
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName()));
				 			sb.append("'");
				 		}else {
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName())+" ");
				 		}
						if( i!=fields.size()-1 ) {
							sb.append(", ");
						}
				  }
				 	
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 sb.append(" WHERE ");
		 for (int i = 0; i < fields.size(); i++) {
			 try {
				 	Id id = fields.get(i).getAnnotation(Id.class);
				 	if(id!=null) {
				 		sb.append(fields.get(i).getName());
				 		sb.append("=");
			 			if( fields.get(i).getType().equals(String.class) ) {
				 			sb.append("'");
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName()));
				 			sb.append("'");
				 		}else {
				 			sb.append(UBean.ejecutarGet(obj, fields.get(i).getName())+" ");
				 		}
				 	}
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		
		try {
			 UConexion uconexion = UConexion.getInstance();
			 PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			 ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static Object obtenerPorId(Class c, Object id) {
		
		Annotation anotacion = c.getAnnotation(Table.class);
		Field[] fields2 = c.getFields();
		Constructor constructor = null;
		Object instancia = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(((com.ar.Anotaciones.Table)anotacion).nombre());
		sb.append(" WHERE ");
	
		 for (int i = 0; i < fields2.length; i++) {
			 try {
				 	Id id2 = fields2[i].getAnnotation(Id.class);
				 	if(id2!=null) {
				 		sb.append(fields2[i].getName());
				 		sb.append("=");
			 			//if( id.getClass().equals(String.class) ) {
				 		//	sb.append("'");
				 		//	sb.append(id);
				 		//	sb.append("'");
				 		//}else {
				 			sb.append(id);
				 		//}
				 	}
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		
		 
		try {
			UConexion uconexion = UConexion.getInstance();
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			ResultSet resp = ps.executeQuery();
			
			while(resp.next()) {
			
				 constructor = c.getConstructor( null );
				 instancia = constructor.newInstance( null );
				 ArrayList<Field> fields = UBean.obtenerAtributos(instancia);
				 for (int i = 0; i < fields.size(); i++) {
					  Column columna = fields.get(i).getAnnotation(Column.class);
					  if( columna!=null ) {
						  UBean.ejecutarSet(instancia, fields.get(i).getName(), resp.getObject(columna.nombre(), fields.get(i).getType()) );
					  }		
				 }
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return instancia;
	}
	
	public static Object ObtenerUltimoRegistroAgregado(Class c) {
		
		Annotation anotacion = c.getAnnotation(Table.class);
		Field[] fields2 = c.getFields();
		Constructor constructor = null;
		Object instancia = null;

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(((com.ar.Anotaciones.Table)anotacion).nombre());
		sb.append(" ORDER BY ");
	
		 for (int i = 0; i < fields2.length; i++) {
			 try {
				 	Id id2 = fields2[i].getAnnotation(Id.class);
				 	if(id2!=null) {
				 		sb.append(fields2[i].getName());
				 	}
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
		 sb.append(" DESC LIMIT 1");
		
		 
		try {
			UConexion uconexion = UConexion.getInstance();
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			ResultSet resp = ps.executeQuery();
			
			while(resp.next()) {
			
				 constructor = c.getConstructor( null );
				 instancia = constructor.newInstance( null );
				 ArrayList<Field> fields = UBean.obtenerAtributos(instancia);
				 for (int i = 0; i < fields.size(); i++) {
					 Column columna = fields.get(i).getAnnotation(Column.class);
					  if( columna!=null ) {
						  UBean.ejecutarSet(instancia, fields.get(i).getName(), resp.getObject(columna.nombre(), fields.get(i).getType()) );
					  }		
				 }
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return instancia;
	}
	
	public static void  guardarModificar(Object obj) {
		
		Field[] fields2 = obj.getClass().getFields();
		Object nameId = null;
		for (int i = 0; i < fields2.length; i++) {
			 try {
				 	Id id2 = fields2[i].getAnnotation(Id.class);
				 	if(id2!=null) {
				 	  nameId = UBean.ejecutarGet(obj, fields2[i].getName());
				 	}
				} catch (Exception e) {
					e.printStackTrace();
				}
		 }
	 
	   if( nameId==null || Consultas.obtenerPorId(obj.getClass(), nameId) == null){
		   Consultas.guardar(obj);	
	   }else {
		   Consultas.modificar(obj);
	   }
	}
	
	
	public static ArrayList<Object> obtenerTodos(Class c) {
		
		Annotation anotacion = c.getAnnotation(Table.class);
		Field[] fields2 = c.getFields();
		Constructor constructor = null;
		ArrayList<Object> lista = new ArrayList<Object>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM ");
		sb.append(((com.ar.Anotaciones.Table)anotacion).nombre());
				
		 
		try {
			UConexion uconexion = UConexion.getInstance();
			PreparedStatement ps = uconexion.getCon().prepareStatement(sb.toString());
			ResultSet resp = ps.executeQuery();
			
			while(resp.next()) {
			
				 constructor = c.getConstructor( null );
				 Object instancia = null;instancia = constructor.newInstance( null );
				 ArrayList<Field> fields = UBean.obtenerAtributos(instancia);
				 for (int i = 0; i < fields.size(); i++) {
					 Column columna = fields.get(i).getAnnotation(Column.class);
					  if( columna!=null ) {
						  UBean.ejecutarSet(instancia, fields.get(i).getName(), resp.getObject(columna.nombre(), fields.get(i).getType()) );
					  }		
				 }
				 lista.add(instancia);
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		return lista;
	}
	
}
