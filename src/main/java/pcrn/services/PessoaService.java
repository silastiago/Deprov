package pcrn.services;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import pcrn.model.Pessoa;
import pcrn.repository.Pessoas;
import pcrn.util.jpa.Transactional;

public class PessoaService implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Pessoas pessoas;

	@Transactional
	public List<Pessoa> listar(){
		return pessoas.listar();
	}
	
	@Transactional
	public Pessoa porCodigo(int codigo){
		return pessoas.porCodigo(codigo);
	}
	
	@Transactional
	public void salvar(Pessoa pessoa){
		pessoas.salvar(pessoa);
	}
	
	@Transactional
	public void remover(Pessoa pessoa){
		pessoas.remover(pessoa);
	}
	
	@Transactional
	public Pessoa porLogin(String login){
		return pessoas.porLogin(login);
	}
}
