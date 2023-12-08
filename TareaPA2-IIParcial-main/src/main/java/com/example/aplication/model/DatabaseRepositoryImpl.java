package com.example.aplication.model;

import java.io.IOException;

import com.example.aplication.data.Historial;
import com.example.aplication.data.HistorialResponse;
import com.example.aplication.data.Paciente;
import com.example.aplication.data.PacientesResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

public class DatabaseRepositoryImpl {
	
	private static DatabaseRepositoryImpl INSTANCE;
	private static DatabaseClient client;
	
	private DatabaseRepositoryImpl(String url, Long timeout) {
		client=new DatabaseClient(url, timeout);
	}
	
	public static DatabaseRepositoryImpl getInstance(String url, Long timeout) {
		if(INSTANCE==null) {
			synchronized (DatabaseRepositoryImpl.class) {

					INSTANCE=new DatabaseRepositoryImpl(url, timeout);

			}
		}
		return INSTANCE;
	}
	
	public PacientesResponse consultarPacientes() throws IOException {
		Call<PacientesResponse> call=client.getDatabase().consultarPacientes();
		Response<PacientesResponse> response=call.execute();
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
	
	public boolean crearPacientes(Paciente nuevo) throws IOException {
		Call<ResponseBody> call=client.getDatabase().crearPacientes(nuevo);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
	}

	public HistorialResponse consultarHistorial() throws IOException {
	    Call<HistorialResponse> call = client.getDatabase().consultarHistorial();
	    Response<HistorialResponse> response = call.execute();
	    if (response.isSuccessful()) {
	        return response.body();
	    } else {
	        return null;
	    }
	}


	public boolean salvarHistorial(Historial historial) throws IOException {
		Call<ResponseBody> call=client.getDatabase().crearHistorial(historial);
		Response<ResponseBody> response=call.execute();
		return response.isSuccessful();
		
	}
	
	
	
	
}
