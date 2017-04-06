package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Seguro;
import pcrn.repository.Seguros;
import pcrn.util.jpa.Transactional;

public class SeguroService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Seguros seguros;
	
	@Transactional
	public List<Seguro> listar(){
		return seguros.listar();
	}
	
	@Transactional
	public Seguro porCodigo(int codigo){
		return seguros.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Seguro seguro){
		seguros.salvar(seguro);
	}
	
	@Transactional
	public void remover(Seguro seguro){
		seguros.remover(seguro);
	}
	
	@Transactional
	public Seguro pegaCodigo(String seguroBusca){
		return seguros.pegaCodigo(seguroBusca);
	}
}
