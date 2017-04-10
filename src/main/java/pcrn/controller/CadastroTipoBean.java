package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Tipo;
import pcrn.services.TipoService;

@Named
@RequestScoped
public class CadastroTipoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoService tipoService;
	
	private Tipo tipo = new Tipo();
	private List<Tipo> listaTipos = new ArrayList<Tipo>();
	
	public void cadastrar(){
		tipoService.salvar(tipo);
	}

	public void excluir(Tipo tipo){
		tipoService.remover(tipo);
	}

	public List<Tipo> listaTipos(){
		listaTipos = tipoService.listar();
		return listaTipos;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Tipo> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<Tipo> listaTipos) {
		this.listaTipos = listaTipos;
	}
}