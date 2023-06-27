package com.modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.conexiones.Conexiones;

public abstract class Abstract_DAO {
	// PROPIEDADES COMUNES PARA SU USO EN TODOS LOS DAOS.
	protected Connection conexion;
	protected Statement sta;
	protected ResultSet rs;
	protected ResourceBundle rb;
	protected PreparedStatement pta;
	protected CallableStatement cta;

	// *********** ZONA DE CONSTRUCTORES ***************
	/**
	 * Establecemos la conexion a la BD segun la operativa automatica.
	 */
	public Abstract_DAO() {
		// INICIAMOS EL PROCESO DE OBTENCION DE CONEXION
		Conexiones con = new Conexiones();
		// SOLICITAMOS CONEXION SEGUN LA ESTRATEGIA QUE NECESITEMOS
		con.crear_ConexionAutomatica();
		// RECOGEMOS LA CONEXION OBTENIDA
		conexion = con.getConexion();
		// CARGAMOS EL FICHERO DE TEXTO CON LAS SENTENCIAS SQL A USAR
		rb = ResourceBundle.getBundle("com.jdbc.sql");
	}

	/**
	 * Establecemos la conexion a la BD segun la operativa manual de peticion
	 * concreta de conexion.<br/>
	 * El parametro del constructor esta definido por las constantes de clase de
	 * Conexion.
	 */
	public Abstract_DAO(int tipo_conexion) {
		// INICIAMOS EL PROCESO DE OBTENCION DE CONEXION
		Conexiones con = new Conexiones();
		// SOLICITAMOS CONEXION SEGUN LA ESTRATEGIA QUE NECESITEMOS
		con.crear_ConexionManual(tipo_conexion);
		// RECOGEMOS LA CONEXION OBTENIDA
		conexion = con.getConexion();
		// CARGAMOS EL FICHERO DE TEXTO CON LAS SENTENCIAS SQL A USAR
		rb = ResourceBundle.getBundle("com.jdbc.sql");
	}

	// *********** FIN ZONA DE CONSTRUCTORES ***************

	/**
	 * Liberamos recursos para implementar el modelo desconectado.<BR/>
	 * El proceso sera en el orden inverso de creacion.
	 * 
	 * @throws SQLException
	 */
	public void liberar_Recursos() throws SQLException {
		if (rs != null) {
			// CERRAMOS EL RESULTSET
			rs.close();
		}
		if (pta != null) {
			// CERRAMOS EL PREPARESTATEMENT
			pta.close();
		}
		if (sta != null) {
			// CERRAMOS EL STATEMENT
			sta.close();
		}
		if (cta != null) {
			// CERRAMOS EL CALLABLESTATEMENT
			cta.close();
		}
		if (conexion != null) {
			// CERRAMOS LA CONEXION
			conexion.close();
		}
	}
}
