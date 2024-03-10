package com.example.demo.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Genero;
import com.example.demo.model.Reporte;
import com.example.demo.model.Biblioteca;

import com.example.demo.service.GeneroService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.example.demo.service.BibliotecaService;

@Controller
@RequestMapping("/biblioteca")
public class BibliotecaController {

	@Autowired
	private BibliotecaService bibliotecaService;
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping("/bibliotecas")
	
	public String getAllBibliotec(Model model) {
		List<Biblioteca> lisBibliotecas = bibliotecaService.getAllBibliotecs();
		model.addAttribute("bibliotecas", lisBibliotecas);
        return "bibliotecList";
	} 
        
    @GetMapping("/report")
    public void report(HttpServletResponse response) throws JRException, IOException {
        		
        		// 1. Acceder al reporte 
        		InputStream jasperStream = this.getClass().getResourceAsStream("/reportes/primerReporte.jasper");
        		
        		// 2. Preparar los datos 
        		Map<String, Object> params = new HashMap<>();
        		params.put("", "julio quispe");
        		List<Biblioteca> lisBibliotecas = bibliotecaService.getAllBibliotecs();
 		
        		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lisBibliotecas);

        		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
        		
        		// 3. Configuracion 
        		
        		response.setContentType("application/x-pdf");
        		response.setHeader("Content-disposition", "filename=Reporte_Biblioteca.pdf");
        		
        		// 4. Exportar reporte
        		  final OutputStream outputStream = response.getOutputStream();

     	        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
          
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("generos", generoService.getAllGeneros());
		return "bibliotecRegister";
	}
		
	@PostMapping("/register")////////////////////////////////////////////////////////////////
	public String createBibliotec(	@RequestParam("name") String name, 
									@RequestParam("nombreAutor") String nombreAutor,
									@RequestParam("fecha") String fecha,
									@RequestParam("id") Long id, Model model) {
		
		Biblioteca biblioteca = new Biblioteca();
		
		biblioteca.nombreLibro	= name;
		biblioteca.nombreAutor	= nombreAutor;
		biblioteca.fecha 		= fecha;
		
		Genero genero = generoService.getGeneroById(id);

		biblioteca.genero = genero;
		
		bibliotecaService.createBiblioteca(biblioteca);
		
		model.addAttribute("bibliotecas", bibliotecaService.getAllBibliotecs());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "bibliotecList";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		
		Biblioteca biblioteca = bibliotecaService.getBibliotecaByID(id);
		
		model.addAttribute("biblioteca", biblioteca);
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "bibliotecEdit";
	}
	
	
	@PostMapping("/edit")
	public String createBibliotec(	@RequestParam("id") Long id, 
									@RequestParam("name") String name,
									@RequestParam("nombreAutor") String nombreAutor,
									@RequestParam("fecha") String fecha,
									@RequestParam("idGenero") Long idGenero, Model model) {
		
		Biblioteca biblioteca = bibliotecaService.getBibliotecaByID(id);
		biblioteca.nombreLibro	= name;
		biblioteca.nombreAutor	= nombreAutor;
		biblioteca.fecha 		= fecha;
				
		Genero genero = generoService.getGeneroById(idGenero);

		biblioteca.genero = genero;
		
		bibliotecaService.createBiblioteca(biblioteca);
		
		model.addAttribute("bibliotecas", bibliotecaService.getAllBibliotecs());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "bibliotecList";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, Model model) {
		bibliotecaService.deleteBibliotec(id);
		
		model.addAttribute("bibliotecas", bibliotecaService.getAllBibliotecs());
		model.addAttribute("generos", generoService.getAllGeneros());
		
		return "bibliotecList";
	}
	
	
	
}