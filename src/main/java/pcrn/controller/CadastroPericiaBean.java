package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Pericia;
import pcrn.services.PericiaService;
import pcrn.util.FacesUtil;

@Named
@RequestScoped
public class CadastroPericiaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PericiaService periciaService;
	
	private Pericia pericia = new Pericia();	
	private List<Pericia> listaPericias = new ArrayList<Pericia>();
	
	public void cadastrar(){
		
		periciaService.salvar(pericia);	
		FacesUtil.addInfoMessage("Exame Veicular cadastrado com sucesso");		
	}
	
	public void editar(){
		
		periciaService.salvar(pericia);	
		FacesUtil.addInfoMessage("Exame Veicular alterado com sucesso");		
	}
	
	
	public void excluir(Pericia pericia){
		
		periciaService.remover(pericia);
		FacesUtil.addInfoMessage("Exame Veicular: " +pericia.getPericia()+ " removido com sucesso");
		
	}
	
	public List<Pericia> listaPericia(){
		
		listaPericias = periciaService.listar();
		
		return listaPericias;
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