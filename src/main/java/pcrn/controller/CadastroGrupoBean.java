package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Grupo;
import pcrn.services.GrupoService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroGrupoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private GrupoService grupoService;
	private Grupo grupo = new Grupo();
	private Grupo grupoSelecionado; 
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();

	public String cadastrar(){
		
		grupoService.salvar(grupo);		
		String pagina = "/site/Grupo/Consulta/Grupo.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Grupo cadastrado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}

	public String editar(){
		
		grupoService.salvar(grupo);
		String pagina = "/site/Grupo/Consulta/Grupo.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Grupo alterado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	public void excluir(){
		
		grupoService.remover(grupoSelecionado);
		FacesUtil.addInfoMessage("Grupo: " +grupoSelecionado.getNome()+ " removido com sucesso");
	}
	
	public List<Grupo> listarGrupos(){
		
		listaGrupos = grupoService.listar();
		
		return listaGrupos;
	}
	
	public String novo(){
		
		String pagina = "/site/Grupo/Novo/Grupo.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Grupo/Edicao/Grupo.xhtml?codigo="+grupoSelecionado.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
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