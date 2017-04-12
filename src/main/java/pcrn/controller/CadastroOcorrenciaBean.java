package pcrn.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

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
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
	private Veiculo veiculo = new Veiculo();
	
	public void cadastrar(String codigo) throws IOException {
		int idVeiculo = Integer.parseInt(codigo);
		veiculo.setCodigo(idVeiculo);
		ocorrencia.setVeiculo(veiculo);
		ocorrenciaService.salvar(ocorrencia);
		
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+codigo);
	}
	
	public String editar(String codigoVeiculo) throws IOException {
		int idVeiculo = Integer.parseInt(codigoVeiculo);
		veiculo.setCodigo(idVeiculo);
		ocorrencia.setVeiculo(veiculo);
		ocorrenciaService.salvar(ocorrencia);
		
	    //FacesContext.getCurrentInstance().getExternalContext().redirect("/Deprov/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo);
	    return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+codigoVeiculo+"faces-redirect=true";
	}
	
	
	/** Este metodo redireciona para a pagina de ocorrencias de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador do veiculo.
	 * 	@return, retorna a pagina das ocorrencias daquele veiculo.
	*/
	public String redirecionar(String codigo){
		
		return "Ocorrencia2.xhtml?codigo_ocorrencia="+codigo+"faces-redirect=true";
	}
	
	/** Este metodo redireciona para a pagina de ocorrencias de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador do veiculo.
	 * 	@return, retorna a pagina das ocorrencias daquele veiculo.
	*/
	public String voltar(String codigo){
		return "/site/Veiculo/Edicao/Veiculo.xhtml?codigo="+codigo+"faces-redirect=true";
	}
	
	
	/** Este metodo remove uma ocorrencia de um determinado veiculo.
	 * 	@param codigo, este codigo � o identificador da ocorrencia.
	*/
	public String excluir(int codigoOcorrencia) throws IOException {
		System.out.println("Entrou no metodo de remoção");
		
		ocorrenciaService.remover(codigoOcorrencia);
		FacesUtil.addInfoMessage("Ocorrencia: " +ocorrencia.getOcorrencia()+ " removida com sucesso");
		
		
		return "/site/Ocorrencia/Consulta/Ocorrencia.xhtml?codigoVeiculo="+ocorrencia.getVeiculo().getCodigo()+"faces-redirect=true";
		
	}
	
	/** Este metodo lista todoas as ocorrencias de um determinado veiculo.
	 * 	@return List<Ocorrencia>, retorna a lista de ocorrencias daquele veiculo.
	*/
	public List<Ocorrencia> listarOcorrencias(String codigo){
		
		//Convertendo a string codigo que � o id do veiculo para um inteiro.
		int idVeiculo = Integer.parseInt(codigo);
		//A lista de ocorrencias recebe as ocorrencias daquele veiculo que se passou a identificador.
		ocorrencias = ocorrenciaService.porCodigoVeiculo(idVeiculo);
		//retorna a lista das ocorrencias daquele veiculo.
		return ocorrencias;
	}

	public void onRowEdit(RowEditEvent event) throws IOException {
		Ocorrencia novaOcorrencia = (Ocorrencia) event.getObject();
		ocorrenciaService.salvar(novaOcorrencia);
		String codigo = novaOcorrencia.getVeiculo().getCodigo().toString();
	    //FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+codigo);
		FacesContext.getCurrentInstance().getExternalContext().redirect("/site/Home.xhtml");
	    
    }
     
    public void onRowCancel(RowEditEvent event) {
        
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