package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Situacao;
import pcrn.repository.Situacoes;
import pcrn.util.jpa.Transactional;

public class SituacaoService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Situacoes situacoes;
	
	@Transactional
	public List<Situacao> listar(){
		return situacoes.listar();
	}
	
	@Transactional
	public Situacao porCodigo(int codigo){
		return situacoes.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Situacao situacao){
		situacoes.salvar(situacao);
	}
	
	@Transactional
	public void remover(Situacao situacao){
		situacoes.remover(situacao);
	}
	
	@Transactional
	public Situacao pegaCodigo(String situacaoBusca){
		return situacoes.pegaCodigo(situacaoBusca);
	}
}
