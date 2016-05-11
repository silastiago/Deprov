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
import model.Modelo;
import repository.Fabricantes;
import repository.Modelos;
import util.Repositorios;

@ManagedBean(name="cadastroModeloBean")
@RequestScoped
public class CadastroModeloBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Modelo modelo  = new Modelo();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private List<Fabricante> fabricantes = new ArrayList<Fabricante>();
	

	@PostConstruct
	public void init(){
		Modelos modelos = this.repositorios.getModelos();
		Fabricantes fabricantes = this.repositorios.getFabricantes();
		this.modelos = modelos.listar();
		this.fabricantes = fabricantes.listar();
	}


	public void cadastrar(){
		Modelos modelos = this.repositorios.getModelos();
		modelos.salvar(modelo);
		this.modelo = new Modelo();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Modelo modelo){
		Modelos modelos = this.repositorios.getModelos();
		modelos.editar(modelo);
	}

	public void excluir(Modelo modelo){
		Modelos modelos = this.repositorios.getModelos();
		modelos.remover(modelo);
		this.init();
	}
	
	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) throws CloneNotSupportedException {
		this.modelo = modelo;
		if (this.modelo == null) {
			this.modelo = new Modelo();
		}else{
			this.modelo = (Modelo) modelo.clone();
		}
	}

	public List<Modelo> getModelos() {
		return modelos;
	}

	public void setModelos(List<Modelo> modelos) {
		this.modelos = modelos;
	}


	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}


	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}
}
