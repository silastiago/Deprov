package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Situacao;
import pcrn.services.SituacaoService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroSituacaoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SituacaoService situacaoService;
	
	private Situacao situacao = new Situacao();
	private Situacao situacaoSelecionada;
	private List<Situacao> listaSituacoes = new ArrayList<Situacao>();
	
	public String cadastrar(){
		situacaoService.salvar(situacao);
		String pagina = "/site/Situacao/Consulta/Situacao.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Situacao cadastrada com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	public String editar(){
		situacaoService.salvar(situacao);
		String pagina = "/site/Situacao/Consulta/Situacao.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Situacao alterada com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	public List<Situacao> listarSituacoes(){
		listaSituacoes = situacaoService.listar();
		//Esta linha retorna a lista de cores
		return listaSituacoes;
	}

	public void excluir(){
		situacaoService.remover(situacaoSelecionada);
		FacesUtil.addInfoMessage("Situacao: " +situacaoSelecionada.getSituacao()+ " removida com sucesso");
	}

	public String novo(){
		
		String pagina = "/site/Situacao/Novo/Situacao.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Situacao/Edicao/Situacao.xhtml?codigo="+situacaoSelecionada.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	public Situacao getSituacaoSelecionada() {
		return situacaoSelecionada;
	}

	public void setSituacaoSelecionada(Situacao situacaoSelecionada) {
		this.situacaoSelecionada = situacaoSelecionada;
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
