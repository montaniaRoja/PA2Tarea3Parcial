package com.example.aplication.controller;

import java.io.IOException;

import com.example.aplication.data.Historial;
import com.example.aplication.data.HistorialResponse;
import com.example.aplication.historial.HistorialView;
import com.example.aplication.historial.HistorialViewModel;
import com.example.aplication.model.DatabaseRepositoryImpl;

public class HistorialInteractorImpl implements HistorialInteractor {
	
	private DatabaseRepositoryImpl modelo;
	private HistorialViewModel vista;

	public HistorialInteractorImpl(HistorialView historialView) {
		super();
		this.vista=historialView;
		this.modelo= DatabaseRepositoryImpl.getInstance("https://apex.oracle.com",30000L);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public void consultarHistorial() {
		try {
			this.modelo.consultarHistorial();
			HistorialResponse respuesta=this.modelo.consultarHistorial();
			this.vista.mostrarHistorialEnGrid(respuesta.getItems());
		}catch(IOException e) {
			
		}
		
	}
	
	public void crearHistorial(Historial historial) {
		try {
			this.modelo.salvarHistorial(historial);
			System.out.println("historial salvado linea 40");
			boolean respuesta=this.modelo.salvarHistorial(historial);
			if(respuesta) {
				this.vista.mostrarMensaje("Exito");
			}
			else {
				this.vista.mostrarMensaje("Fallo");
			}
			
			}catch(IOException e) {
			
		}
		
	
	}
	

	
}
