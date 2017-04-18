package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Fabricante;
import pcrn.model.Modelo;
import pcrn.services.ModeloService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroModeloBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ModeloService modeloService;
	
	private Modelo modelo  = new Modelo();
	private Modelo modeloSelecionado;
	private List<Modelo> listaModelos = new ArrayList<Modelo>();
	
	
	public String cadastrar(){
		
		modeloService.salvar(modelo);		
		String pagina = "/site/Modelo/Consulta/Modelo.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Modelo cadastrado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}

	public String editar(){
		modeloService.salvar(modelo);		
		String pagina = "/site/Modelo/Consulta/Modelo.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Modelo alterado com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
	}
	
	/** Este metodo Remove um modelo.
	*  @param modelo, Este modelo � o objeto Modelo que voc� ir� remover.
	*/
	public void excluir(){
		modeloService.remover(modeloSelecionado);
		FacesUtil.addInfoMessage("Modelo: " +modeloSelecionado.getModelo()+ " removido com sucesso");
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

	public String novo(){
		
		String pagina = "/site/Modelo/Novo/Modelo.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Modelo/Edicao/Modelo.xhtml?codigo="+modeloSelecionado.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	
	public Modelo getModeloSelecionado() {
		return modeloSelecionado;
	}

	public void setModeloSelecionado(Modelo modeloSelecionado) {
		this.modeloSelecionado = modeloSelecionado;
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