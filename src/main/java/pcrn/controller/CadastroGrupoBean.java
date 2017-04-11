package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Grupo;
import pcrn.services.GrupoService;
import pcrn.util.FacesUtil;

@Named
@RequestScoped
public class CadastroGrupoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoService grupoService;
	private Grupo grupo = new Grupo(); 	
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();

	public void cadastrar(){
		grupoService.salvar(grupo);
		FacesUtil.addInfoMessage("Grupo cadastrado com sucesso");
	}

	public void editar(){
		
		grupoService.salvar(grupo);
		FacesUtil.addInfoMessage("Grupo alterado com sucesso");
	}
	
	public void excluir(Grupo grupo){
		
		grupoService.remover(grupo);
		FacesUtil.addInfoMessage("Grupo: " +grupo.getNome()+ " removido com sucesso");
	}
	
	public List<Grupo> listarGrupos(){
		
		listaGrupos = grupoService.listar();
		
		return listaGrupos;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
}