package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Situacao;
import pcrn.services.SituacaoService;

@Named
@RequestScoped
public class CadastroSituacaoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SituacaoService situacaoService;
	
	private Situacao situacao = new Situacao();	
	private List<Situacao> listaSituacoes = new ArrayList<Situacao>();
	
	public void cadastrar(){
		situacaoService.salvar(situacao);
	}
	
	public List<Situacao> listarSituacoes(){
		listaSituacoes = situacaoService.listar();
		//Esta linha retorna a lista de cores
		return listaSituacoes;
	}

	public void excluir(Situacao situacao){
		situacaoService.remover(situacao);
	}

}
