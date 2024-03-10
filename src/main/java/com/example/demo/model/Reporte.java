package com.example.demo.model;

public class Reporte {

	public long id;
	public String nombreLibro;
	public String nombreAutor;
	public String fecha;
	
	public Reporte(long id, String nombreLibro, String nombreAutor, String fecha) {
		super();
		this.id = id;
		this.nombreLibro = nombreLibro;
		this.nombreAutor = nombreAutor;
		this.fecha = fecha;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreLibro() {
		return nombreLibro;
	}

	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}

	public String getNombreAutor() {
		return nombreAutor;
	}

	public void setNombreAutor(String nombreAutor) {
		this.nombreAutor = nombreAutor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	
}