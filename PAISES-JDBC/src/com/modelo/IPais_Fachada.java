package com.modelo;

public interface IPais_Fachada {

	/**
	 * Llamada al proceso de alta desde la fachada. El cierre de la conexion se
	 * gestiona a nivel de fachada.
	 * 
	 * @param pais_nuevo Informacion a incliur en al base de datos.
	 * @return
	 */
	boolean alta_Pais(Pais pais_nuevo);

	boolean modificacion_Pais(Pais pais_amodificar);

	boolean baja_Pais(Pais pais_aeliminar);

	boolean consultar_todo();

}