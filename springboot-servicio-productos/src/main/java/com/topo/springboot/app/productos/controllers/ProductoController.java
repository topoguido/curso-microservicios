package com.topo.springboot.app.productos.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.topo.springboot.app.productos.models.entity.Producto;
import com.topo.springboot.app.productos.models.service.IProductoService;

@RestController
public class ProductoController {
	
	@Autowired
	private IProductoService productoService;
	
	@Autowired
	private Environment env;

	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;

	
	@GetMapping("/listar")
	public List<Producto> listar(){
		return productoService.findAll().stream().map( producto -> {
				producto.setPort(webServerAppCtxt.getWebServer().getPort());
				return producto;
			}).collect(Collectors.toList());
	}
	
	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		Producto producto = productoService.findById(id);
		producto.setPort(Integer.parseInt(env.getProperty("local.server.port")));
		return producto;
	}

}
