package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Seguro;
import pcrn.services.SeguroService;
import pcrn.util.FacesUtil;

@Named
@RequestScoped
public class CadastroSeguroBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SeguroService seguroService;
	
	private Seguro seguro = new Seguro();
	private List<Seguro> listaSeguros = new ArrayList<Seguro>();
	
	public void cadastrar(){
		seguroService.salvar(seguro);
		FacesUtil.addInfoMessage("Seguro cadastrado com sucesso");
	}

	public void editar(){
		seguroService.salvar(seguro);
		FacesUtil.addInfoMessage("Seguro alterado com sucesso");
	}
	
	public void excluir(Seguro seguro){
		seguroService.remover(seguro);
		FacesUtil.addInfoMessage("Seguro: " +seguro.getSeguro()+ " removido com sucesso");
	}
	
	public List<Seguro> listaSeguro(){
		listaSeguros = seguroService.listar();
		return listaSeguros;
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