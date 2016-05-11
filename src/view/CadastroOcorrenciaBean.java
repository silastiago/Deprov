package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Ocorrencia;
import model.Veiculo;
import repository.Ocorrencias;
import repository.Veiculos;
import util.Repositorios;

@ManagedBean(name="cadastroOcorrrenciaBean")
@RequestScoped
public class CadastroOcorrenciaBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Ocorrencia ocorrencia = new Ocorrencia();	
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
 
	@PostConstruct
	public void init(){
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		this.ocorrencias = ocorrencias.listar();
		Veiculos veiculos = this.repositorios.getveiculos();
		this.veiculos = veiculos.listar();
	}


	public void cadastrar(){
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.salvar(ocorrencia);
		this.ocorrencia = new Ocorrencia();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Ocorrencia ocorrencia){
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.editar(ocorrencia);
		
	}

	public void excluir(Ocorrencia ocorrencia){
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.remover(ocorrencia);
		this.init();
	}


	public Ocorrencia getOcorrencia() {
		return ocorrencia;
	}


	public void setOcorrencia(Ocorrencia ocorrencia) throws CloneNotSupportedException {
		this.ocorrencia = ocorrencia;
		if (this.ocorrencia == null) {
			this.ocorrencia = new Ocorrencia();
		}else{
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
	
}
