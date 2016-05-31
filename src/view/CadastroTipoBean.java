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
import model.Tipo;
import repository.Marcas;
import repository.Tipos;
import util.Repositorios;

@ManagedBean(name="cadastroTipoBean")
@RequestScoped
public class CadastroTipoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Tipo tipo = new Tipo();
	private List<Tipo> tipos = new ArrayList<Tipo>();
	
	

	@PostConstruct
	public void init(){
		Tipos tipos = this.repositorios.getTipos();
		this.tipos = tipos.listar();
	}


	public String cadastrar(){
		Tipos tipos = this.repositorios.getTipos();
		tipos.salvar(tipo);

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		return "index?faces-redirect=true";
	}

	public void update(Tipo tipo){
		Tipos tipos = this.repositorios.getTipos();
		tipos.editar(tipo);
	}

	public void excluir(Tipo tipo){
		Tipos tipos = this.repositorios.getTipos();
		tipos.remover(tipo);
		this.init();
	}


	public Tipo getTipo() {
		return tipo;
	}


	public void setTipo(Tipo tipo) throws CloneNotSupportedException {
		this.tipo = tipo;
		if (this.tipo == null) {
			this.tipo = new Tipo();
		}else{
			this.tipo = (Tipo) tipo.clone();
		}
	}


	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}
}
