package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Fabricante;
import repository.Fabricantes;
import util.Repositorios;

@ManagedBean(name="cadastroFabricanteBean")
@RequestScoped
public class CadastroFabricanteBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Fabricante fabricante = new Fabricante();
	private List<Fabricante> fabricantes = new ArrayList<Fabricante>();
	

	@PostConstruct
	public void init(){
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		this.fabricantes = fabricantes.listar();
	}


	public String cadastrar(){
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		fabricantes.salvar(fabricante);
		
		return "index?faces-redirect=true";
	}

	public void excluir(Fabricante fabricante){
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		fabricantes.remover(fabricante);
		this.init();
	}

	public Fabricante getFabricante() {
		return fabricante;
	}


	public void setFabricante(Fabricante fabricante) throws CloneNotSupportedException {
		this.fabricante = fabricante;
		if (this.fabricante == null) {
			this.fabricante = new Fabricante();
		}else {
			this.fabricante = (Fabricante) fabricante.clone();
		}
	}


	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}


	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}


	
}
