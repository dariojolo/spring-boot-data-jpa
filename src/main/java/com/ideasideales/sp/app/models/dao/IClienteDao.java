package com.ideasideales.sp.app.models.dao;

import java.util.List;

import com.ideasideales.sp.app.models.entities.Cliente;

public interface IClienteDao {
	
	public List<Cliente>findAll();
	
	public void save(Cliente cliente);

}
