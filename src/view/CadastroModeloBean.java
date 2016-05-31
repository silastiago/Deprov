package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Marca;
import model.Modelo;
import repository.Marcas;
import repository.Modelos;
import util.Repositorios;

@ManagedBean(name="cadastroModeloBean")
@RequestScoped
public class CadastroModeloBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Modelo modelo  = new Modelo();
	private List<Modelo> modelos = new ArrayList<Modelo>();
	private List<Marca> marcas = new ArrayList<Marca>();
	

	@PostConstruct
	public void init(){
		Modelos modelos = this.repositorios.getModelos();
		Marcas marcas = this.repositorios.getMarcas();
		this.modelos = modelos.listar();
		this.marcas = marcas.listar();
	}


	public String cadastrar(){
		Modelos modelos = this.repositorios.getModelos();
		modelos.salvar(modelo);
		

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		
		return "index?faces-redirect=true";
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

	public List<Marca> getMarcas() {
		return marcas;
	}

	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}
}