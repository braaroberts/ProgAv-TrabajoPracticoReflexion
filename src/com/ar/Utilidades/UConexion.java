package com.ar.Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UConexion {
	
	private static UConexion instancia;
	private String driver;
	private String pathConection;
	private String user;
	private String pass;
	
	private static Connection con=null;
	
	private UConexion() {
		this.driver = "com.mysql.jdbc.Driver";
		this.pathConection = "jdbc:mysql://localhost:3306/test";
		this.user= "root";
		this.pass="";
		
		if( this.con.equals(null) ) {
			try {
				this.crearConexion();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			
		}
	}
	
	private void crearConexion() throws SQLException, ClassNotFoundException {
		Class.forName(this.driver);
		Connection  con = DriverManager.getConnection(this.pathConection,this.user,this.pass);
	}

	public static UConexion getInstance() {
		if(instancia.equals(null)) {
			 instancia = new UConexion();
			 return instancia;
		}else {
			return instancia;
		}
		
	}

	
	
	
	
}
