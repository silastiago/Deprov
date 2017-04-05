package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Local;
import pcrn.repository.Locais;
import pcrn.util.jpa.Transactional;

public class LocalService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Locais locais;
	
	@Transactional
	public List<Local> listar(){
		return locais.listar();
	}

	@Transactional
	public Local porCodigo(int codigo){
		return locais.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Local local){
		locais.salvar(local);
	}
	
	@Transactional
	public void remover(Local local){
		locais.remover(local);
	}
	
	@Transactional
	public Local pegaCodigo(String localBusca){
		return locais.pegaCodigo(localBusca);
	}
}