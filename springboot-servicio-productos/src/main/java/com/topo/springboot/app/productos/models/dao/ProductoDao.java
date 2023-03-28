package com.topo.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.topo.springboot.app.productos.models.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Long>{

}
