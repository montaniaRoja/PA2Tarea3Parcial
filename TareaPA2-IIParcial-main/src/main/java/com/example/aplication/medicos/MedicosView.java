package com.example.aplication.medicos;


import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collection;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import com.example.aplication.controller.MedicosInteractor;

import com.example.aplication.controller.MedicosInteractorImpl;
import com.example.aplication.data.*;
import com.example.aplication.inicio.MainLayout;



@Route(value = "medicos", layout = MainLayout.class)
@PageTitle("Medicos | Hospital CRM")

public class MedicosView extends Div implements MedicosViewModel, BeforeEnterObserver {
	
	private static final long serialVersionUID = 1L;
	private Grid<Medico> grid = new Grid<>(Medico.class);
	TextField filterText = new TextField();
    MedicoForm form;
    private static MedicosInteractor controlador;
    private List<Medico> elementos;
    
    public MedicosView() {
        addClassName("list-view"); 
        setSizeFull();
        configureGrid();
        configureForm();
        controlador=new MedicosInteractorImpl(this);
        this.setElementos(new ArrayList<>());
        add(getToolbar(), getContent()); 
            
    
        controlador.consultarMedicos(); //ESTE ES PARA traer los medicos desde oracle
        
        closeEditor();
    }
    
    private void setElementos(List<Medico> items) {
		// TODO Auto-generated method stub
		
	}

	private void closeEditor() {
    	form.setMedico(null);
        form.setVisible(false);
        removeClassName("editing");
		
	}
    
    private void addMedico() { 
        //grid.asSingleSelect().clear();
        form.carnet.clear();
        form.nombre.clear();    // al crear el formulario buscar esto
        form.telefono.clear();  // al crear el formulario buscar esto 
        form.correo.clear();
        form.especialidad.clear();
        
        form.setVisible(true);
        editMedico(new Medico());
    }
    

	private void configureForm() {
		form = new MedicoForm(); 
        form.setWidth("25em");
		
	}


	private void configureGrid() {
		 grid.addClassNames("medico-grid"); 
	        grid.setSizeFull();
	        grid.setColumns("carnet","nombre", "telefono", "correo","especialidad"); 
	        
	        grid.getColumns().forEach(col -> col.setAutoWidth(true)); 
	        
	        grid.asSingleSelect().addValueChangeListener(event ->
	        editMedico(event.getValue()));
		
	}
	
	public void editMedico(Medico medico) { 
        if (medico == null) {
            closeEditor();
        } else {
           //form.setPaciente(paciente);//aqui llenamos el formulario con los datos del paciente pero como no me funciono lo hice con todos los campos
           form.carnet.setValue(medico.getCarnet());
           form.nombre.setValue(medico.getNombre());
           form.telefono.setValue(medico.getTelefono());
           form.correo.setValue(medico.getCorreo());
           form.especialidad.setValue(medico.getEspecialidad());
                   
           
           form.setVisible(true);
            addClassName("editing");
        }
    }
	
	private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filtrar por nombre...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY); 

        Button addMedicoButton = new Button("Agregar Medico");
        addMedicoButton.setId("addMedico");
        addMedicoButton.addClickListener(click -> addMedico());
        var toolbar = new HorizontalLayout(filterText, addMedicoButton); 
        toolbar.addClassName("toolbar"); 
        return toolbar;
    }
	
	 private Component getContent() {
	        HorizontalLayout content = new HorizontalLayout(grid,form );
	         
	        content.setFlexGrow(1, form);
	        content.setFlexGrow(2, grid);
	        content.addClassNames("content");
	        content.setSizeFull();
	        return content;
	    }
	 
	 @Override
		public void mostrarMedicosEnGrid(List<Medico> items) {
			Collection<Medico> itemsCollections=items;
			grid.setItems(itemsCollections);
			setElementos(items);
		}
    


	@Override
	public void beforeEnter(BeforeEnterEvent event) {
		// TODO Auto-generated method stub
		
	}

	public List<Medico> getElementos() {
		return elementos;
	}

	@Override
	public void mostrarMensaje(String string) {
		Notification.show(string);
		// TODO Auto-generated method stub
		
	}
	
	public static void nuevoMedico(Medico medico) {
	    try {
	        controlador.crearMedico(medico);
	        // Después de agregar el médico, vuelve a consultar la lista de médicos y actualiza el Grid
	        controlador.consultarMedicos();
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        Notification.show("Error al guardar el medico: " + ex.getMessage(), 3000, Notification.Position.TOP_CENTER);
	    }
	}

	

}
