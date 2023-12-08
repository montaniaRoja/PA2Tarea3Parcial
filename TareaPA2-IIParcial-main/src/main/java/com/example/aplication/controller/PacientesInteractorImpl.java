package com.example.aplication.controller;

import java.io.IOException;

import com.example.aplication.data.Paciente;
import com.example.aplication.data.PacientesResponse;
import com.example.aplication.model.DatabaseRepositoryImpl;
import com.example.aplication.pacientes.PacientesView;
import com.example.aplication.pacientes.PacientesViewModel;

public class PacientesInteractorImpl implements PacientesInteractor{
	
	
	private DatabaseRepositoryImpl modelo;
	private PacientesViewModel vista;
	
	
	public PacientesInteractorImpl(PacientesViewModel view) {
		
		super();
		this.vista=view;
		this.modelo= DatabaseRepositoryImpl.getInstance("https://apex.oracle.com",30000L);
		
		
		
	}
	
	@Override
	public void consultarPacientes() {
		try {
			this.modelo.consultarPacientes();
			PacientesResponse respuesta=this.modelo.consultarPacientes();
			this.vista.mostrarPacientesEnGrid(respuesta.getItems());
		}catch(IOException e) {
			
		}
		
		
	}

	@Override
	public void crearPacientes(Paciente nuevo) {
		try {
			this.modelo.crearPacientes(nuevo);
			boolean respuesta=this.modelo.crearPacientes(nuevo);
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
