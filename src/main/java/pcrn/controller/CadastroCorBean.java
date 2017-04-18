package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Cor;
import pcrn.services.CorService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroCorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CorService corService;
	
	private Cor cor = new Cor();
	private Cor corSelecionada;
	private List<Cor> listaCores = new ArrayList<Cor>();
	
	
	public String cadastrar(){
		
		corService.salvar(cor);		
		String pagina = "/site/Cor/Consulta/Cor.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Cor cadastrada com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
		
	}
	
	public String editar(){
		
		corService.salvar(cor);
		String pagina = "/site/Cor/Consulta/Cor.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Cor alterada com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de todas as cores cadastradas no sistema.
	*/
	public List<Cor> listarCores(){
		
		//Esta linha lista as cores e joga em uma lista de cores.
		listaCores = corService.listar();
		//Esta linha retorna a lista de cores
		return listaCores;
	}

	/** Este metodo Remove uma cor.
	*  @param cor, Esta cor é o objeto cor que você irá remover.
	*/
	public void excluir(){
		//Esta linha remove a cor.
		corService.remover(corSelecionada);
		FacesUtil.addInfoMessage("Cor: " +corSelecionada.getCor()+ " removida com sucesso");
	}
	
	
	public String novo(){
		
		String pagina = "/site/Cor/Novo/Cor.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Cor/Edicao/Cor.xhtml?codigo="+corSelecionada.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	public Cor getCorSelecionada() {
		return corSelecionada;
	}

	public void setCorSelecionada(Cor corSelecionada) {
		this.corSelecionada = corSelecionada;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public List<Cor> getListaCores() {
		return listaCores;
	}

	public void setListaCores(List<Cor> listaCores) {
		this.listaCores = listaCores;
	}
	
}