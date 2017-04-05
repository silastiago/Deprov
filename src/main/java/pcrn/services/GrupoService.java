package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Grupo;
import pcrn.repository.Grupos;
import pcrn.util.jpa.Transactional;

public class GrupoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Grupos grupos;
	
	@Transactional
	public List<Grupo> listar(){
		return grupos.listar();
	}
	@Transactional
	public Grupo porCodigo(int codigo){
		return grupos.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Grupo grupo){
		grupos.salvar(grupo);
	}

	@Transactional
	public void remover(Grupo grupo){
		grupos.remover(grupo);
	}
}
