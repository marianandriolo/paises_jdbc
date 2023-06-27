package com.modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pais_Fachada implements IPais_Fachada {
		private Pais_DAO pais_dao;

		public Pais_Fachada() {
			pais_dao = new Pais_DAO();
		}

		/**
		 * Llamada al proceso de alta desde la fachada. El cierre de la conexion se
		 * gestiona a nivel de fachada.
		 * 
		 * @param pais_nuevo Informacion a incliur en al base de datos.
		 * @return
		 */
		@Override
		public boolean alta_Pais(Pais pais_nuevo) {
			boolean alta_correcta = false;
			try {
				pais_dao.alta_Pais(pais_nuevo);
				alta_correcta = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pais_dao.liberar_Recursos();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return alta_correcta;
		}
		
		@Override
		public boolean baja_Pais(Pais pais_aeliminar) {
			boolean baja_correcta = false;
			try {
				pais_dao.baja_Pais(pais_aeliminar);
				baja_correcta = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pais_dao.liberar_Recursos();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return baja_correcta;
		}
		
		@Override
		public boolean modificacion_Pais(Pais pais_amodificar) {
			boolean modificacion_correcta = false;
			try {
				pais_dao.modificacion_pais(pais_amodificar);
				modificacion_correcta = true;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pais_dao.liberar_Recursos();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return modificacion_correcta;
		}
		
		public boolean consultar_todo() {
			boolean consulta_correcta = false;
			List<Pais> lista_paises = new ArrayList<>();
			try {
				lista_paises = pais_dao.consultar_todo();
				for (Pais paises : lista_paises) {
					System.out.println(paises);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error en la consulta");
			}
			return consulta_correcta;
		}

	}

