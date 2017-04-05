package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Fabricante;
import pcrn.repository.Fabricantes;
import pcrn.util.jpa.Transactional;

public class FabricanteService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Fabricantes fabricantes;
	
	@Transactional
	public List<Fabricante> listar(){
		return fabricantes.listar();
	}
	
	@Transactional
	public Fabricante porCodigo(int codigo){
		return fabricantes.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Fabricante fabricante){
		fabricantes.salvar(fabricante);
	}
	
	@Transactional
	public void remover(Fabricante fabricante){
		fabricantes.remover(fabricante);
	}
	
	@Transactional
	public Fabricante pegaCodigo(String fabricanteBusca){
		return fabricantes.pegaCodigo(fabricanteBusca);
	}
}