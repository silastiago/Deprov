package view;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

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
	private List<Veiculo> veiculos = new ArrayList<Veiculo>();
	private Veiculo veiculo = new Veiculo();
	
	@PostConstruct
	public void init(){
		ocorrencias = listarVeiculo();
	}
	
	public void cadastrar(String codigo) throws IOException {
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		
		int idVeiculo = Integer.parseInt(codigo);		
		veiculo.setCodigo(idVeiculo);
		ocorrencia.setVeiculo(veiculo);
		ocorrencias.salvar(ocorrencia);
		
		FacesContext fc = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+codigo);
		
		//return null;
	}
	
	public String redirecionar(String codigo){
		//Ocorrencia ocorrencia = (Ocorrencia) event.getComponent().getAttributes().get("codigo_ocorrencia");
		System.out.println("Codigo da ocorrencia: "+codigo);
		String pagina = "Ocorrencia2.xhtml?codigo_ocorrencia="+codigo+"faces-redirect=true";
		System.out.println(pagina);
		return pagina;
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

	public void excluir(String codigo) throws IOException {
		String codigo2 = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
		System.out.println("Codigo da ocorrencia: "+ codigo);
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		int idOcorrencia = Integer.parseInt(codigo);
		Ocorrencia ocorrencia2 = ocorrencias.porCodigo(idOcorrencia);
		ocorrencias.remover(ocorrencia2);
		
		FacesContext fc = FacesContext.getCurrentInstance();
	    HttpSession session = (HttpSession)fc.getExternalContext().getSession(false);
	    FacesContext.getCurrentInstance().getExternalContext().redirect("Ocorrencia.xhtml?codigo="+ocorrencia2.getVeiculo().getCodigo());
		//return "index?faces-redirect=true";
	}

	public List<Ocorrencia> listar(){
		Ocorrencias Iocorrencia = this.repositorios.getocorrencia();
		ocorrencias = Iocorrencia.listar();
		return ocorrencias; 
	}
	
	public List<Ocorrencia> listarVeiculo(){
		String codigo = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("codigo");
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

	public void onRowEdit(RowEditEvent event) throws IOException {
		Ocorrencia novaOcorrencia = (Ocorrencia) event.getObject();
		Ocorrencias ocorrencias = this.repositorios.getocorrencia();
		ocorrencias.editar(novaOcorrencia);
        
    }
     
    public void onRowCancel(RowEditEvent event) {
        
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