package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Pericia;
import pcrn.services.PericiaService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroPericiaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PericiaService periciaService;
	
	private Pericia pericia = new Pericia();
	private Pericia periciaSelecionada;
	private List<Pericia> listaPericias = new ArrayList<Pericia>();
	
	public String cadastrar(){
		
		periciaService.salvar(pericia);
		String pagina = "/site/ExameVeicular/Consulta/ExameVeicular.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Exame Veicular cadastrado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	public String editar(){
		
		periciaService.salvar(pericia);	
		
		String pagina = "/site/ExameVeicular/Consulta/ExameVeicular.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Exame Veicular alterado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
				
	}
	
	
	public void excluir(){
		
		periciaService.remover(periciaSelecionada);
		FacesUtil.addInfoMessage("Exame Veicular: " +periciaSelecionada.getPericia()+ " removido com sucesso");
		
	}
	
	public List<Pericia> listaPericia(){
		
		listaPericias = periciaService.listar();
		
		return listaPericias;
	}
	
	public String novo(){
		
		String pagina = "/site/ExameVeicular/Novo/ExameVeicular.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/ExameVeicular/Edicao/ExameVeicular.xhtml?codigo="+periciaSelecionada.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	
	public Pericia getPericiaSelecionada() {
		return periciaSelecionada;
	}

	public void setPericiaSelecionada(Pericia periciaSelecionada) {
		this.periciaSelecionada = periciaSelecionada;
	}

	public Pericia getPericia() {
		return pericia;
	}

	public void setPericia(Pericia pericia) {
		this.pericia = pericia;
	}

	public List<Pericia> getListaPericias() {
		return listaPericias;
	}

	public void setListaPericias(List<Pericia> listaPericias) {
		this.listaPericias = listaPericias;
	}	
}