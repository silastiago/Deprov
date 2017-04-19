package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Ocorrencia;

import pcrn.model.Veiculo;
import pcrn.services.OcorrenciaService;
import pcrn.util.FacesUtil;

@Named
@ViewScoped
public class CadastroOcorrenciaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@Inject
	private OcorrenciaService ocorrenciaService;
	
	private Ocorrencia ocorrencia = new Ocorrencia();
	private Ocorrencia ocorrenciaSelecionada;
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
	private Veiculo veiculo = new Veiculo();
	
	
	@PostConstruct
	public void init(){
		ocorrencias = this.listarOcorrencias();
	}
	
	
	public String cadastrar(String codigoVeiculo) throws IOException {
		
		int idVeiculo = Integer.parseInt(codigoVeiculo);
		veiculo.setCodigo(idVeiculo);
		ocorrencia.setVeiculo(veiculo);
		ocorrenciaService.salvar(ocorrencia);
		FacesUtil.addInfoMessage("Ocorrencia cadastrada com sucesso");		
		FacesUtil.contextFlash();
		
		return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
	}
	
	public String editar(String codigoVeiculo) throws IOException {
		int idVeiculo = Integer.parseInt(codigoVeiculo);
		veiculo.setCodigo(idVeiculo);
		ocorrencia.setVeiculo(veiculo);
		ocorrenciaService.salvar(ocorrencia);
		FacesUtil.addInfoMessage("Ocorrencia alterada com sucesso");		
		FacesUtil.contextFlash();
		
	    //FacesContext.getCurrentInstance().getExternalContext().redirect("/Deprov/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo);
	    return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
	}
	
	/** Este metodo redireciona para a pagina de ocorrencias de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador do veiculo.
	 * 	@return, retorna a pagina das ocorrencias daquele veiculo.
	*/
	public String voltar(String codigoVeiculo){
		
		String pagina = "/site/Veiculo/Edicao/Veiculo.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
		
		return pagina;
	}
	
	
	public String novo(String codigoVeiculo){
		
		String pagina = "/site/Ocorrencia/Novo/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Ocorrencia/Edicao/Ocorrencia.xhtml?codigoOcorrencia="+ocorrenciaSelecionada.getCodigo()+"&codigoVeiculo="+ocorrenciaSelecionada.getVeiculo().getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	
	
	/** Este metodo remove uma ocorrencia de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador da ocorrencia.
	*/
	public String excluir(String codigoVeiculo) throws IOException {
		
		
		System.out.println("Entrou no metodo de remoção");
		
		ocorrenciaService.remover(ocorrenciaSelecionada);
		FacesUtil.addInfoMessage("Ocorrencia: " +ocorrenciaSelecionada.getOcorrencia()+ " removida com sucesso");
		FacesUtil.contextFlash();
		
		return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
		
	}
	
	/** Este metodo lista todoas as ocorrencias de um determinado veiculo.
	 * 	@return List<Ocorrencia>, retorna a lista de ocorrencias daquele veiculo.
	*/
	public List<Ocorrencia> listarOcorrencias(){
		
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigoVeiculo");
		//Convertendo a string codigo que o id do veiculo para um inteiro.
		int idVeiculo = Integer.parseInt(codigo);
		//A lista de ocorrencias recebe as ocorrencias daquele veiculo que se passou a identificador.
		ocorrencias = ocorrenciaService.porCodigoVeiculo(idVeiculo);
		//retorna a lista das ocorrencias daquele veiculo.
		return ocorrencias;
	}  
    
	public Ocorrencia getOcorrenciaSelecionada() {
		return ocorrenciaSelecionada;
	}

	public void setOcorrenciaSelecionada(Ocorrencia ocorrenciaSelecionada) {
		this.ocorrenciaSelecionada = ocorrenciaSelecionada;
	}

	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) {
		this.ocorrencia = ocorrencia;
	}

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}	
}