package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Local;
import pcrn.services.LocalService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroLocalBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LocalService localService;
	
	private Local local = new Local();
	private Local localSelecionado;
	private List<Local> listaLocais = new ArrayList<Local>();
	
	public String cadastrar(){
		
		localService.salvar(local);		
		String pagina = "/site/Local/Consulta/Local.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Local cadastrado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	public String editar(){
		
		localService.salvar(local);
		String pagina = "/site/Local/Consulta/Local.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Local alterado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	public List<Local> listarLocais(){
		listaLocais = localService.listar();
		return listaLocais;
	}
	
	public void excluir(){
		localService.remover(localSelecionado);
		FacesUtil.addInfoMessage("Local: " +localSelecionado.getLocal()+ " removido com sucesso");
	}
	
	public String novo(){
		
		String pagina = "/site/Local/Novo/Local.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Local/Edicao/Local.xhtml?codigo="+localSelecionado.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	public Local getLocalSelecionado() {
		return localSelecionado;
	}

	public void setLocalSelecionado(Local localSelecionado) {
		this.localSelecionado = localSelecionado;
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