package com.example.aplication.medicos;

import java.util.List;

import com.example.aplication.data.Medico;

public interface MedicosViewModel {
	
	void mostrarMedicosEnGrid(List<Medico> items);

	void mostrarMensaje(String string);

}
