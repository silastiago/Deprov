package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.CellEditEvent;

import model.Ocorrencia;
import model.Veiculo;
import repository.Ocorrencias;
import repository.Veiculos;
import util.Repositorios;

@ManagedBean(name = "cadastroOcorrrenciaBean")
@ViewScoped
public class CadastroOcorrenciaBean implements Serializable {

	private Repositorios repositorios = new Repositorios();
	private Ocorrencia ocorrencia = new Ocorrencia();
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private Veiculo veiculo = new Veiculo();

	@PostConstruct
	public void init() {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		this.ocorrencias = ocorrencias.listar();
		Veiculos veiculos = this.repositorios.getveiculos();
		this.veiculos = veiculos.listar();
	}

	public String cadastrar(String codigo) {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		
		int idVeiculo = Integer.parseInt(codigo);
		
		
		//String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");

		
		//System.out.println("Codigo do veiculo: " + codigo);
		
		// FacesContext fc = FacesContext.getCurrentInstance();
		// String id_veiculo =
		// fc.getExternalContext().getRequestParameterMap().get("codigo_veiculo")
		// ;
		System.out.println("ID do veiculo: " + codigo);
		// 
		//System.out.println("ID do veiculo: " + idVeiculo);
		
		veiculo.setCodigo(idVeiculo);
		ocorrencia.setVeiculo(veiculo);

		ocorrencias.salvar(ocorrencia);
		

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	
		return "index?faces-redirect=true";
	}
	
	public void update(Ocorrencia ocorrencia) {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.editar(ocorrencia);
	}

	public void excluir(Ocorrencia ocorrencia) {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.remover(ocorrencia);
		this.init();
	}

	public List<Ocorrencia> listar(){
		Ocorrencias Iocorrencia = this.repositorios.getocorrencia();
		ocorrencias = Iocorrencia.listar();
		return ocorrencias; 
	}
	
	public List<Ocorrencia> listarVeiculo(String codigo){
		int idVeiculo = Integer.parseInt(codigo);
		Ocorrencias Iocorrencia = this.repositorios.getocorrencia();
		ocorrencias = Iocorrencia.porCodigoVeiculo(idVeiculo);
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

	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	

}
