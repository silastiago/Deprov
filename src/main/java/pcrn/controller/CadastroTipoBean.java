package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Tipo;
import pcrn.services.TipoService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroTipoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private TipoService tipoService;
	
	private Tipo tipo = new Tipo();
	private Tipo tipoSelecionado;
	private List<Tipo> listaTipos = new ArrayList<Tipo>();
	
	public String cadastrar(){
		tipoService.salvar(tipo);
		String pagina = "/site/Tipo/Consulta/Tipo.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Tipo cadastrado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}

	public String editar(){
		tipoService.salvar(tipo);
		String pagina = "/site/Tipo/Consulta/Tipo.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Tipo alterado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	public void excluir(){
		tipoService.remover(tipoSelecionado);
		FacesUtil.addInfoMessage("Tipo: " +tipoSelecionado.getTipo()+ " removido com sucesso");
	}

	public List<Tipo> listaTipos(){
		listaTipos = tipoService.listar();
		return listaTipos;
	}
	
	public String novo(){
		
		String pagina = "/site/Tipo/Novo/Tipo.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Tipo/Edicao/Tipo.xhtml?codigo="+tipoSelecionado.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	public Tipo getTipoSelecionado() {
		return tipoSelecionado;
	}

	public void setTipoSelecionado(Tipo tipoSelecionado) {
		this.tipoSelecionado = tipoSelecionado;
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