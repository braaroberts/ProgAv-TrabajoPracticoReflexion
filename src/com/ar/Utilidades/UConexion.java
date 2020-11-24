package com.ar.Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ar.Properties.ConfigProperties;

public class UConexion {
	
	private static UConexion instancia;
	private String driver;
	private String pathConection;
	private String user;
	private String pass;
	
	private static Connection con=null;
	
	private UConexion() {
		//this.driver = "com.mysql.jdbc.Driver";
		//this.pathConection = "jdbc:mysql://localhost:3306/test";
		//this.user= "root";
		//this.pass="";
		this.driver = ConfigProperties.getPropiedad("driver");
		this.pathConection = ConfigProperties.getPropiedad("pathConection");
		this.user= ConfigProperties.getPropiedad("user");
		this.pass=ConfigProperties.getPropiedad("pass");
		System.out.println("this.driver: "+this.driver+" - this.pathConection: "+this.pathConection+" - this.user: "+this.user+" - this.pass: "+this.pass);
		if( this.con == null ) {
			try {
				this.crearConexion();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public Connection getCon() {
		return con;
	}


	private void crearConexion() throws SQLException, ClassNotFoundException {
		Class.forName(this.driver);
		this.con = DriverManager.getConnection(this.pathConection,this.user,this.pass);
	}
	

	public static UConexion getInstance() {
		if(instancia == null) {
			 instancia = new UConexion();
			 return instancia;
		}else {
			return instancia;
		}
		
	}

	
	
	
	
}
