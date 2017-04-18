package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Seguro;
import pcrn.services.SeguroService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroSeguroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SeguroService seguroService;
	
	private Seguro seguro = new Seguro();
	private Seguro seguroSelecionado;
	private List<Seguro> listaSeguros = new ArrayList<Seguro>();
	
	public String cadastrar(){
		
		seguroService.salvar(seguro);
		String pagina = "/site/Seguro/Consulta/Seguro.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Seguro cadastrado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;	
		
	}

	public String editar(){
		
		seguroService.salvar(seguro);
		String pagina = "/site/Seguro/Consulta/Seguro.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Seguro alterado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	public void excluir(){
		seguroService.remover(seguroSelecionado);
		FacesUtil.addInfoMessage("Seguro: " +seguroSelecionado.getSeguro()+ " removido com sucesso");
	}
	
	public List<Seguro> listaSeguro(){
		listaSeguros = seguroService.listar();
		return listaSeguros;
	}

	public String novo(){
		
		String pagina = "/site/Seguro/Novo/Seguro.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Seguro/Edicao/Seguro.xhtml?codigo="+seguroSelecionado.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	
	public Seguro getSeguroSelecionado() {
		return seguroSelecionado;
	}

	public void setSeguroSelecionado(Seguro seguroSelecionado) {
		this.seguroSelecionado = seguroSelecionado;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public List<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(List<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}
}