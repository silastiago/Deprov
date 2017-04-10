package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Fabricante;
import pcrn.services.FabricanteService;

@Named
@RequestScoped
public class CadastroFabricanteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private FabricanteService fabricanteService;
	
	
	private Fabricante fabricante = new Fabricante();
	List<Fabricante> listaFabricantes = new ArrayList<Fabricante>();
	
	/** Este metodo cadastra um fabricante.
	*/
	public void cadastrar(){
		
		fabricanteService.salvar(fabricante);
		
	}
	
	
	public void excluir(Fabricante fabricante){
		
		fabricanteService.remover(fabricante);
		
	}
	
	public List<Fabricante> listaFabricantes(){
		
		listaFabricantes = fabricanteService.listar();
		
		return listaFabricantes;		
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
