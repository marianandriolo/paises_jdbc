package com.modelo;


import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class Pais_DAO extends Abstract_DAO {
	public Pais_DAO() {
		super();
	}


	public Pais_DAO(int tipo_conexion) {
		super(tipo_conexion);
	}

	public void alta_Pais(Pais pais_nuevo) throws SQLException {
		// RECOGEMOS EL SQL DEL FICHERO
		String sql_alta = rb.getString("pais.alta");
		// CREACION DEL PREPARESTATEMENT PARA LANZAR LA ORDEN
		pta = conexion.prepareStatement(sql_alta);
		// SUSTITUIMOS LAS VARIABLES POR SUS VALORES
		pta.setLong(1, pais_nuevo.getCodigo_pais());
		pta.setInt(2, pais_nuevo.getPais_isonum());
		pta.setString(3, pais_nuevo.getPais_iso2());
		pta.setString(4, pais_nuevo.getPais_iso3());
		pta.setString(5, pais_nuevo.getPais_nombre());
		// REALIZAR LA ORDEN
		pta.execute();
	}
	
	public void baja_Pais(Pais pais_aeliminar)throws SQLException{
		String sql_baja = rb.getString("pais.baja");
		pta = conexion.prepareStatement(sql_baja);
		
		pta.setLong(1, pais_aeliminar.getCodigo_pais());
		
		pta.execute();
	}
	
	public void modificacion_pais(Pais pais_amodificar) throws SQLException{
		String sql_modificacion = rb.getString("pais.modificacion");
		pta = conexion.prepareStatement(sql_modificacion);
		

		pta.setInt(1, pais_amodificar.getPais_isonum());
		pta.setString(2, pais_amodificar.getPais_iso2());
		pta.setString(3, pais_amodificar.getPais_iso3());
		pta.setString(4, pais_amodificar.getPais_nombre());		
		pta.setLong(5, pais_amodificar.getCodigo_pais());
		
		pta.execute();
		
	}
	
	public List<Pais> consultar_todo() throws SQLException{
		String sql_consultarTodo = rb.getString("consulta.paises.todos");
		pta = conexion.prepareStatement(sql_consultarTodo);
		rs = pta.executeQuery();
		
		
		Pais pais_nuevo = null;
		List<Pais> lista_paises = new ArrayList<>();

		while (rs.next()) {
			pais_nuevo = new Pais();
			pais_nuevo.setCodigo_pais(rs.getLong("numero_pais"));
			pais_nuevo.setPais_iso2(rs.getString("pais_iso2"));
			pais_nuevo.setPais_iso3(rs.getString("pais_iso3"));
			pais_nuevo.setPais_isonum(rs.getInt("pais_isonum"));
			pais_nuevo.setPais_nombre(rs.getString("pais_nombre"));
			lista_paises.add(pais_nuevo);

		}
		return lista_paises;
	}

}
