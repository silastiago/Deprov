package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Cor;
import pcrn.repository.Cores;
import pcrn.util.jpa.Transactional;

public class CorService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cores cores;
	
	@Transactional
	public List<Cor> listar(){
		return cores.listar();
	}
	
	@Transactional
	public Cor porCodigo(int codigo){
		return cores.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Cor cor){
		cores.salvar(cor);
	}
	
	@Transactional
	public void remover(Cor cor){
		cores.remover(cor);
	}
	
	@Transactional
	public Cor pegaCodigo(String corBusca){
		return cores.pegaCodigo(corBusca);
	}

}
