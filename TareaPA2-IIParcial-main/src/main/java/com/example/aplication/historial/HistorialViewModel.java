package com.example.aplication.historial;

import java.util.List;

import com.example.aplication.data.Historial;

public interface HistorialViewModel {

	void mostrarHistorialEnGrid(List<Historial> items);

	void mostrarMensaje(String string);

}
