package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Pericia;
import model.Seguro;
import repository.Pericias;
import repository.Seguros;
import util.Repositorios;

@ManagedBean(name="cadastroSeguroBean")
@RequestScoped
public class CadastroSeguroBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Seguro seguro = new Seguro();
	private List<Seguro> seguros = new ArrayList<Seguro>();	
	
	

	@PostConstruct
	public void init(){
		Seguros seguros = this.repositorios.getSeguros();
		this.seguros = seguros.listar();
		
		
	}


	public void cadastrar(){
		Seguros seguros = this.repositorios.getSeguros();
		seguros.salvar(seguro);
		this.seguro = new Seguro();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Seguro seguro){
		Seguros seguros = this.repositorios.getSeguros();
		seguros.editar(seguro);
	}

	public void excluir(Seguro seguro){
		Seguros seguros = this.repositorios.getSeguros();
		seguros.remover(seguro);
		this.init();
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) throws CloneNotSupportedException {
		this.seguro = seguro;
		if (this.seguro == null) {			
			this.seguro = new Seguro();
		}else{
			this.seguro = (Seguro) seguro.clone();
		}
	}

	public List<Seguro> getSeguros() {
		return seguros;
	}

	public void setSeguros(List<Seguro> seguros) {
		this.seguros = seguros;
	}
}