package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Local;
import pcrn.services.LocalService;
import pcrn.util.FacesUtil;

@Named
@RequestScoped
public class CadastroLocalBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LocalService localService;
	
	private Local local = new Local();
	private List<Local> listaLocais = new ArrayList<Local>();
	
	public void cadastrar(){
		
		localService.salvar(local);
		FacesUtil.addInfoMessage("Local cadastrado com sucesso");
	}
	
	public void editar(){
		
		localService.salvar(local);
		FacesUtil.addInfoMessage("Modelo alterado com sucesso");
	}
	
	public List<Local> listarLocais(){
		listaLocais = localService.listar();
		return listaLocais;
	}
	
	public void excluir(Local local){
		localService.remover(local);
		FacesUtil.addInfoMessage("Local: " +local.getLocal()+ " removido com sucesso");
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public List<Local> getListaLocais() {
		return listaLocais;
	}

	public void setListaLocais(List<Local> listaLocais) {
		this.listaLocais = listaLocais;
	}
}