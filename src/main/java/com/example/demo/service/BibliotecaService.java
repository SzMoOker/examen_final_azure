package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Biblioteca;
import com.example.demo.repository.BibliotecaRepository;

@Service
public class BibliotecaService {

	@Autowired
	private BibliotecaRepository bibliotecaRepository;
	
	public List<Biblioteca> getAllBibliotecs() {
		return bibliotecaRepository.findAll();
	}
	
	
	public Biblioteca createBiblioteca(Biblioteca biblioteca) {
		return bibliotecaRepository.save(biblioteca);
	}
	
	public Biblioteca getBibliotecaByID(Long id) {
		return bibliotecaRepository.findById(id).orElse(null);
	}
	
	public void deleteBibliotec(Long id) {
		bibliotecaRepository.deleteById(id);
	}
	
	
}