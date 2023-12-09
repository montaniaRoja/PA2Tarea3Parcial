package com.example.aplication.controller;
import java.io.IOException;

import com.example.aplication.data.Medico;
import com.example.aplication.data.MedicoResponse;
import com.example.aplication.medicos.MedicosView;
import com.example.aplication.medicos.MedicosViewModel;
import com.example.aplication.model.DatabaseRepositoryImpl;

public class MedicosInteractorImpl implements MedicosInteractor{
	
	
	private DatabaseRepositoryImpl modelo;
	private MedicosViewModel vista;
	
	
	public MedicosInteractorImpl(MedicosViewModel view) {
		
		super();
		this.vista=view;
		this.modelo= DatabaseRepositoryImpl.getInstance("https://apex.oracle.com",30000L);
		
		
		
	}
	
	@Override
	public void consultarMedicos() {
		try {
			this.modelo.consultarMedico();
			MedicoResponse respuesta=this.modelo.consultarMedico();
			this.vista.mostrarMedicosEnGrid(respuesta.getItems());
		}catch(IOException e) {
			
		}
		
		
	}

	@Override
	public void crearMedico(Medico nuevo) {
		try {
			this.modelo.crearMedico(nuevo);
			boolean respuesta=this.modelo.crearMedico(nuevo);
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
