package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Pericia;
import pcrn.repository.Pericias;
import pcrn.util.jpa.Transactional;

public class PericiaService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pericias pericias;
	
	@Transactional
	public List<Pericia> listar(){
		return pericias.listar();
	}
	
	@Transactional
	public Pericia porCodigo(int codigo){
		return pericias.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Pericia pericia){
		pericias.salvar(pericia);
	}
	
	@Transactional
	public void remover(Pericia pericia){
		pericias.remover(pericia);
	}
	
	@Transactional
	public Pericia pegaCodigo(String periciaBusca){
		return pericias.pegaCodigo(periciaBusca);
	}
}