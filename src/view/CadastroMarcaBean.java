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
import repository.Marcas;
import util.Repositorios;

@ManagedBean(name="cadastroMarcaBean")
@RequestScoped
public class CadastroMarcaBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Marca marca = new Marca();
	private List<Marca> marcas = new ArrayList<Marca>();
	

	@PostConstruct
	public void init(){
		Marcas marcas = this.repositorios.getMarcas();
		this.marcas = marcas.listar();
	}


	public String cadastrar(){
		Marcas marcas = this.repositorios.getMarcas();
		marcas.salvar(marca);
		

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		
		return "index?faces-redirect=true";
	}

	public void update(Marca marca){
		Marcas marcas = this.repositorios.getMarcas();
		marcas.editar(marca);
	}

	public void excluir(Marca marca){
		Marcas marcas = this.repositorios.getMarcas();
		marcas.remover(marca);
		this.init();
	}

	public Marca getMarca() {
		return marca;
	}


	public void setMarca(Marca marca) throws CloneNotSupportedException {
		this.marca = marca;
		if (this.marca == null) {
			this.marca = new Marca();
		}else {
			this.marca = (Marca) marca.clone();
		}
	}


	public List<Marca> getMarcas() {
		return marcas;
	}


	public void setMarcas(List<Marca> marcas) {
		this.marcas = marcas;
	}


	
}
