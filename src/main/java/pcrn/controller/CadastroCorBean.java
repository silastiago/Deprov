package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Cor;
import pcrn.services.CorService;
import pcrn.util.FacesUtil;

@Named
@RequestScoped
public class CadastroCorBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CorService corService;
	
	private Cor cor = new Cor();
	private List<Cor> listaCores = new ArrayList<Cor>();
	
	
	public void cadastrar(){
		
		corService.salvar(cor);
		FacesUtil.addInfoMessage("Cor cadastrada com sucesso");
	}
	
	public void editar(){
		
		corService.salvar(cor);
		FacesUtil.addInfoMessage("Cor alterada com sucesso");
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
	public void excluir(Cor cor){
		//Esta linha remove a cor.
		corService.remover(cor);
		FacesUtil.addInfoMessage("Cor: " +cor.getCor()+ " removida com sucesso");
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