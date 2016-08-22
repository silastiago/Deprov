package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Tipo;
import repository.Tipos;
import util.Repositorios;

@ManagedBean(name="cadastroTipoBean")
@RequestScoped
public class CadastroTipoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Tipo tipo = new Tipo();
	private List<Tipo> listaTipos = new ArrayList<Tipo>();
	
	/** Este metodo cadastra um Tipo de veiculo.
	*/
	public void cadastrar(){
		//Esta linha estou instanciando a interface com sua implementacao.
		Tipos tipos = this.repositorios.getTipos();
		//Esta linha salva a entidade Tipo.
		tipos.salvar(tipo);
	}

	public void excluir(Tipo tipo){
		//Esta linha estou instanciando a interface com sua implementacao.
		Tipos tipos = this.repositorios.getTipos();
		//Esta linha remove a entidade Tipo.
		tipos.remover(tipo);
		//Atualizar a lista de tipos
		this.listaTipos();
	}

	public List<Tipo> listaTipos(){
		//Esta linha estou instanciando a interface com sua implementacao.
		Tipos tipos = this.repositorios.getTipos();
		//Esta linha lista os tipos e joga em uma lista de tipos.
		listaTipos = tipos.listar();
		//retorna a lista de tipos.
		return listaTipos;
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

	public List<Tipo> getListaTipos() {
		return listaTipos;
	}

	public void setListaTipos(List<Tipo> listaTipos) {
		this.listaTipos = listaTipos;
	}
}