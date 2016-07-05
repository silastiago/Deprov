package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


import model.Ocorrencia;
import model.Veiculo;
import repository.Ocorrencias;
import util.Repositorios;

@ManagedBean(name = "cadastroOcorrrenciaBean")
@ViewScoped
public class CadastroOcorrenciaBean implements Serializable {

	private Repositorios repositorios = new Repositorios();
	private Ocorrencia ocorrencia = new Ocorrencia();
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
	private List<Ocorrencia> ocorrenciasFiltradas = new ArrayList<Ocorrencia>();
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private Veiculo veiculo = new Veiculo();

	public String cadastrar(String codigo) {
		
		//String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo";)
		
		
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		
		int idVeiculo = Integer.parseInt(codigo);		
		veiculo.setCodigo(idVeiculo);
		ocorrencia.setVeiculo(veiculo);
		ocorrencias.salvar(ocorrencia);
		return null;
	}
	
	public String editar() {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.salvar(ocorrencia);
		return "index?faces-redirect=true";
	}
	
	
	public void update(Ocorrencia ocorrencia) {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.editar(ocorrencia);
	}

	public String excluir(Ocorrencia ocorrencia) {
		System.out.println("codigo da ocorrencia: " + ocorrencia.getCodigo());
		
		//Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		//ocorrencias.remover(ocorrencia);
		return "index?faces-redirect=true";
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

	public List<Ocorrencia> getOcorrenciasFiltradas() {
		return ocorrenciasFiltradas;
	}

	public void setOcorrenciasFiltradas(List<Ocorrencia> ocorrenciasFiltradas) {
		this.ocorrenciasFiltradas = ocorrenciasFiltradas;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
}