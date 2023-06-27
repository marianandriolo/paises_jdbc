package com.controlador;

import java.util.ArrayList;
import java.util.List;

import com.modelo.IPais_Fachada;
import com.modelo.Pais;
import com.modelo.Pais_DAO;
import com.modelo.Pais_Fachada;


public class Inicio {


	public static void main(String[] args) {
		IPais_Fachada pais_fachada = new Pais_Fachada();

		Pais pais_nuevo = new Pais();
		pais_nuevo.setCodigo_pais((long)887);
		pais_nuevo.setPais_nombre("nada");
		pais_nuevo.setPais_iso2("MA");
		pais_nuevo.setPais_iso3("M");
		pais_nuevo.setPais_isonum(887);

		boolean alta_correcta = pais_fachada.alta_Pais(pais_nuevo);
		
		if (alta_correcta) {
			System.out.println("ALTA CORRECTA");
		} else {
			System.out.println("ALTA FALLIDA");
		}

		 pais_nuevo.setPais_nombre("moordor");
		 pais_fachada = new Pais_Fachada();		
	
		 boolean modificacion_correcta = pais_fachada.modificacion_Pais(pais_nuevo);

		 if (modificacion_correcta) {
				System.out.println("MODIFICACION CORRECTA");
			} else {
				System.out.println("MODIFICACION FALLIDA");
			}
		pais_fachada = new Pais_Fachada();
		boolean baja_correcta = pais_fachada.baja_Pais(pais_nuevo);
		 if (baja_correcta) {
				System.out.println("BAJA CORRECTA");
			} else {
				System.out.println("BAJA FALLIDA");
			}
		 pais_fachada = new Pais_Fachada();
		 System.out.println(pais_fachada.consultar_todo());  
	}
	
}
