package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Fabricante;
import pcrn.model.Modelo;
import pcrn.services.ModeloService;
import pcrn.util.FacesUtil;

@Named
@RequestScoped
public class CadastroModeloBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ModeloService modeloService;
	
	private Modelo modelo  = new Modelo();
	private List<Modelo> listaModelos = new ArrayList<Modelo>();
	
	
	public void cadastrar(){
		
		modeloService.salvar(modelo);
		FacesUtil.addInfoMessage("Modelo cadastrado com sucesso");
	}

	public void editar(){
		modeloService.salvar(modelo);
		FacesUtil.addInfoMessage("Modelo alterado com sucesso");
	}
	
	/** Este metodo Remove um modelo.
	*  @param modelo, Este modelo � o objeto Modelo que voc� ir� remover.
	*/
	public void excluir(Modelo modelo){
		modeloService.remover(modelo);
		FacesUtil.addInfoMessage("Modelo: " +modelo.getModelo()+ " removido com sucesso");
	}
	
	/** Este metodo lista todas os modelos cadastrados.
	* 	@return retorna a lista de todos os modelos cadastrados no sistema.
	*/
	public List<Modelo> listaModelo(){
		listaModelos = modeloService.listar();
		return listaModelos;
	}	
	
	public void listarModelos(ValueChangeEvent evento){
		Fabricante fabricante = (Fabricante) evento.getNewValue();
		listaModelos = modeloService.pegaModelos(fabricante.getCodigo());
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public List<Modelo> getListaModelos() {
		return listaModelos;
	}

	public void setListaModelos(List<Modelo> listaModelos) {
		this.listaModelos = listaModelos;
	}
}