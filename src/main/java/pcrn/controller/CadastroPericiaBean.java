package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Pericia;
import pcrn.services.PericiaService;

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
		
	}
	
	public void excluir(Pericia pericia){
		
		periciaService.remover(pericia);
		
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