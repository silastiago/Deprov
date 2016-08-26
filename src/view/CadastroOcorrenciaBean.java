package view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import model.Ocorrencia;
import model.Veiculo;
import repository.Ocorrencias;
import util.Repositorios;

@ManagedBean(name = "cadastroOcorrenciaBean")
@ViewScoped
public class CadastroOcorrenciaBean implements Serializable {

	private Repositorios repositorios = new Repositorios();
	private Ocorrencia ocorrencia = new Ocorrencia();
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
	private Veiculo veiculo = new Veiculo();
	
	//Primeiro metodo a ser executado quando entra nas views referentes as ocorrencias do veiculo.
	@PostConstruct
	public void init(){
		ocorrencias = listarVeiculo();
	}
	
	/** Este metodo cadastra uma ocorrencia do veiculo.
	 * 	@param codigo, este codigo é o identificador do veiculo.
	*/
	public void cadastrar(String codigo) throws IOException {
		//Esta linha estou instanciando a interface com sua implementação.
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		//Convertendo a string codigo que é o id do veiculo para um inteiro.
		int idVeiculo = Integer.parseInt(codigo);		
		//Estamos setando no atributo codigo, o identificador do veiculo.
		veiculo.setCodigo(idVeiculo);
		//Estamos setando no atributo veiculo o veiculo que aquela ocorrencia faz parte.
		ocorrencia.setVeiculo(veiculo);
		//Esta linha salva a entidade ocorrencia.
		ocorrencias.salvar(ocorrencia);
		//Esta linha faz um redirecionamento de pagina para a pagina de ocorrencia daquele veiculo.
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+codigo);
	}
	
	/** Este metodo redireciona para a pagina de ocorrencias de um determinado veiculo.
	 * 	@param codigo, este codigo é o identificador do veiculo.
	 * 	@return, retorna a pagina das ocorrencias daquele veiculo.
	*/
	public String redirecionar(String codigo){
		//A variavel pagina recebe a pagina da ocorrencia com o codigo do veiculo, para ser redirecionada a qual veiculo se quer ver as ocorrencias.
		String pagina = "Ocorrencia2.xhtml?codigo_ocorrencia="+codigo+"faces-redirect=true";
		//Retorna a pagina.
		return pagina;
	}
	
	/** Este metodo remove uma ocorrencia de um determinado veiculo.
	 * 	@param codigo, este codigo é o identificador da ocorrencia.
	*/
	public void excluir(String codigo) throws IOException {
		//Esta linha estou instanciando a interface com sua implementação.
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		//Convertendo a string codigo que é o id da ocorrencia para um inteiro.
		int idOcorrencia = Integer.parseInt(codigo);
		//Recuperando ocorrencia pelo codigo.
		Ocorrencia ocorrencia2 = ocorrencias.porCodigo(idOcorrencia);
		//Esta linha remove a entidade ocorrencia.
		ocorrencias.remover(ocorrencia2);
		//Esta linha faz um redirecionamento de pagina para a pagina de ocorrencia daquele veiculo.
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+ocorrencia2.getVeiculo().getCodigo());
	}
	
	/** Este metodo lista todoas as ocorrencias de um determinado veiculo.
	 * 	@return List<Ocorrencia>, retorna a lista de ocorrencias daquele veiculo.
	*/
	public List<Ocorrencia> listarVeiculo(){
		//A variavel codigo recebe por requisicao o codigo do veiculo.
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		//Convertendo a string codigo que é o id do veiculo para um inteiro.
		int idVeiculo = Integer.parseInt(codigo);
		//Esta linha estou instanciando a interface com sua implementação.
		Ocorrencias Iocorrencia = this.repositorios.getocorrencia();
		//A lista de ocorrencias recebe as ocorrencias daquele veiculo que se passou a identificador.
		ocorrencias = Iocorrencia.porCodigoVeiculo(idVeiculo);
		//retorna a lista das ocorrencias daquele veiculo.
		return ocorrencias;
	}
	
	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}

	public void setOcorrencia(Ocorrencia ocorrencia) throws CloneNotSupportedException {
		this.ocorrencia = ocorrencia;
		if (this.ocorrencia == null) {
			this.ocorrencia = new Ocorrencia();
		} else {
			this.ocorrencia = (Ocorrencia) ocorrencia.clone();
		}
	}

	public void onRowEdit(RowEditEvent event) throws IOException {
		Ocorrencia novaOcorrencia = (Ocorrencia) event.getObject();
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.salvar(novaOcorrencia);
		String codigo = novaOcorrencia.getVeiculo().getCodigo().toString();
		
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+codigo);
    }
     
    public void onRowCancel(RowEditEvent event) {
        
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