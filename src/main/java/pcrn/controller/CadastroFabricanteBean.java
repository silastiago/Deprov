package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Fabricante;
import pcrn.services.FabricanteService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroFabricanteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FabricanteService fabricanteService;
	
	
	private Fabricante fabricante = new Fabricante();
	private Fabricante fabricanteSelecionado;
	private List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();
	
	/** Este metodo cadastra um fabricante.
	*/
	public String cadastrar(){
		
		fabricanteService.salvar(fabricante);
		String pagina = "/site/Fabricante/Consulta/Fabricante.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Marca cadastrada com sucesso");
		FacesUtil.contextFlash();
		
		return pagina;		
		
	}
	
	public String editar(){
		
		fabricanteService.salvar(fabricante);		
		String pagina = "/site/Fabricante/Consulta/Fabricante.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Marca alterada com sucesso");
		FacesUtil.contextFlash();
		
		return pagina;
		
	}
	
	public void excluir(Fabricante fabricante){
		
		fabricanteService.remover(fabricante);
		FacesUtil.addInfoMessage("Marca: " +fabricante.getFabricante()+ " removida com sucesso");
		
	}
	
	public List<Fabricante> listaFabricantes(){
		
		listaFabricantes = fabricanteService.listar();
		
		return listaFabricantes;		
	}
	
	public String novo(){
		
		String pagina = "/site/Fabricante/Novo/Fabricante.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Fabricante/Edicao/Fabricante.xhtml?codigo="+fabricanteSelecionado.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	public Fabricante getFabricanteSelecionado() {
		return fabricanteSelecionado;
	}

	public void setFabricanteSelecionado(Fabricante fabricanteSelecionado) {
		this.fabricanteSelecionado = fabricanteSelecionado;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}


	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}


	public List<Fabricante> getListaFabricantes() {
		return listaFabricantes;
	}


	public void setListaFabricantes(List<Fabricante> listaFabricantes) {
		this.listaFabricantes = listaFabricantes;
	}

}
