package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Tipo;
import pcrn.repository.Tipos;
import pcrn.util.jpa.Transactional;

public class TipoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Tipos tipos;

	@Transactional
	public List<Tipo> listar(){
		return tipos.listar();
	}
	
	@Transactional
	public Tipo porCodigo(int codigo){
		return tipos.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Tipo tipo){
		tipos.salvar(tipo);
	}
	
	@Transactional
	public void remover(Tipo tipo){
		tipos.remover(tipo);
	}	
}
