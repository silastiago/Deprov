package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Situacao;
import pcrn.services.SituacaoService;
import pcrn.util.FacesUtil;

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
		FacesUtil.addInfoMessage("Situacao cadastrada com sucesso");
	}
	
	public void editar(){
		situacaoService.salvar(situacao);
		FacesUtil.addInfoMessage("Situacao alterada com sucesso");
	}
	
	public List<Situacao> listarSituacoes(){
		listaSituacoes = situacaoService.listar();
		//Esta linha retorna a lista de cores
		return listaSituacoes;
	}

	public void excluir(Situacao situacao){
		situacaoService.remover(situacao);
		FacesUtil.addInfoMessage("Situacao: " +situacao.getSituacao()+ " removida com sucesso");
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public List<Situacao> getListaSituacoes() {
		return listaSituacoes;
	}

	public void setListaSituacoes(List<Situacao> listaSituacoes) {
		this.listaSituacoes = listaSituacoes;
	}
}
