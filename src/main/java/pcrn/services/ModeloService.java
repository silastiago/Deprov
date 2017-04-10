package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Modelo;
import pcrn.repository.Modelos;
import pcrn.util.jpa.Transactional;

public class ModeloService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Modelos modelos;
	
	
	@Transactional
	public List<Modelo> listar(){
		return modelos.listar();
	}
	
	@Transactional
	public Modelo porCodigo(int codigo){
		return modelos.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Modelo modelo){
		modelos.salvar(modelo);
	}
	
	@Transactional
	public void remover(Modelo modelo){
		modelos.remover(modelo);
	}

	@Transactional
	public Modelo pegaCodigo(String modeloBusca){
		return modelos.pegaCodigo(modeloBusca);
	}
	
	@Transactional
	public List<Modelo> pegaModelos(int codigo_fabricante){
		return modelos.pegaModelos(codigo_fabricante);
	}	
}