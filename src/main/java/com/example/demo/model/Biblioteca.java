package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "biblioteca")
public class Biblioteca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long id;
	
	@Column(name = "nombreLibro", nullable = false)
	public String nombreLibro;
	
	@Column(name = "nombreAutor", nullable = false)
	public String nombreAutor;
	
	@Column(name = "fecha", nullable = false)
	public String fecha;
	
	@ManyToOne
	@JoinColumn(name = "idGenero", nullable = false)
	public Genero genero;
	
}