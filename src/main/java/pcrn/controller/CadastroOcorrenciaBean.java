package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pcrn.model.Ocorrencia;
import pcrn.model.Veiculo;
import pcrn.services.OcorrenciaService;

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
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
	private Veiculo veiculo = new Veiculo();
	
	public void cadastrar(String codigo) throws IOException {
		int idVeiculo = Integer.parseInt(codigo);		
		veiculo.setCodigo(idVeiculo);
		ocorrencia.setVeiculo(veiculo);
		
		ocorrenciaService.salvar(ocorrencia);
		
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+codigo);
	}
	
	/** Este metodo redireciona para a pagina de ocorrencias de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador do veiculo.
	 * 	@return, retorna a pagina das ocorrencias daquele veiculo.
	*/
	public String redirecionar(String codigo){
		//A variavel pagina recebe a pagina da ocorrencia com o codigo do veiculo, para ser redirecionada a qual veiculo se quer ver as ocorrencias.
		String pagina = "Ocorrencia2.xhtml?codigo_ocorrencia="+codigo+"faces-redirect=true";
		//Retorna a pagina.
		return pagina;
	}
	
	/** Este metodo redireciona para a pagina de ocorrencias de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador do veiculo.
	 * 	@return, retorna a pagina das ocorrencias daquele veiculo.
	*/
	public String voltar(String codigo){
		//A variavel pagina recebe a pagina da ocorrencia com o codigo do veiculo, para ser redirecionada a qual veiculo se quer ver as ocorrencias.
		String pagina = "Veiculo.xhtml?codigo="+codigo+"faces-redirect=true";
		//Retorna a pagina.
		return pagina;
	}
	
	
	/** Este metodo remove uma ocorrencia de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador da ocorrencia.
	*/
	public void excluir(Ocorrencia ocorrencia) throws IOException {
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		//Convertendo a string codigo que � o id do veiculo para um inteiro.
		int idVeiculo = Integer.parseInt(codigo);
		
		ocorrenciaService.remover(ocorrencia);
		
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+idVeiculo);
	}
	
	/** Este metodo lista todoas as ocorrencias de um determinado veiculo.
	 * 	@return List<Ocorrencia>, retorna a lista de ocorrencias daquele veiculo.
	*/
	public List<Ocorrencia> listarVeiculo(){
		//A variavel codigo recebe por requisicao o codigo do veiculo.
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		//Convertendo a string codigo que � o id do veiculo para um inteiro.
		int idVeiculo = Integer.parseInt(codigo);
		//A lista de ocorrencias recebe as ocorrencias daquele veiculo que se passou a identificador.
		ocorrencias = ocorrenciaService.porCodigoVeiculo(idVeiculo);
		//retorna a lista das ocorrencias daquele veiculo.
		return ocorrencias;
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