package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Biblioteca;

public interface BibliotecaRepository extends JpaRepository<Biblioteca, Long> {

}