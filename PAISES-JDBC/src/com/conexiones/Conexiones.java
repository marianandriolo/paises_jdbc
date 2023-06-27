package com.conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Conexiones {
	 private Connection conexion;
	 private ResourceBundle rb;
	 
	 public static final int DESARROLLO = 1;
	 public static final int PRODUCCION = 2;
	 public static final int PREPRODUCCION = 3;
	 public static final int CASA = 4;
	 
	 public Conexiones() {
		 rb = ResourceBundle.getBundle("com.conexiones.conexion");
	 }
	 public void crear_ConexionAutomatica() {
		 String tipoconexion = rb.getString("tipoconexion");
		 this.establecer_conexion(tipoconexion);
	 }
		public void crear_ConexionManual(int tipo_conexion) {
		 String codigo_conexion = null;
		 switch (tipo_conexion) {
		 case DESARROLLO:
			 codigo_conexion = "d";
			 break;
		 case PRODUCCION:
			 codigo_conexion = "p";
			 break;
		 case PREPRODUCCION:
			 codigo_conexion = "pp";
			 break;
		 case CASA:
			 codigo_conexion = "casa";
			 break;
		default:
			break;
		 }
		 if(codigo_conexion !=null) {
			 this.establecer_conexion(codigo_conexion);
		 }
	 }
	 
	 public void establecer_conexion (String tipoconexion) {
		 String url = rb.getString("tipoconexion."+ tipoconexion + ".url");
		 String usuario = rb.getString("tipoconexion." + tipoconexion + ".usuario");
		 String clave = rb.getString("tipoconexion." + tipoconexion + ".clave");
		 String driver = rb.getString("tipoconexion." + tipoconexion + ".driver");
		 boolean valido = true;
		 try {
			 Class.forName(driver);
		 }catch(Exception e) {
			 System.out.println("el driver no esta correcto");
			 valido = false;
		 }
		 if(valido) {
			 try {
				 conexion = DriverManager.getConnection(url, usuario, clave);
			 }catch(SQLException e) {
				 System.out.println("fallo en la conexion con bbdd");
			 }
		 }
	 }	
	 public Connection getConexion() {
			 return conexion;
		 }

}
