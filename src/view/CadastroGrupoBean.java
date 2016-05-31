package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Grupo;
import repository.Grupos;
import util.Repositorios;

@ManagedBean(name="cadastroGrupoBean")
@RequestScoped
public class CadastroGrupoBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Grupo grupo = new Grupo(); 	
	private List<Grupo> grupos = new ArrayList<Grupo>();
	

	@PostConstruct
	public void init(){
		Grupos grupos = this.repositorios.getGrupos();
		this.grupos = grupos.listar();
	}


	public void cadastrar(){
		Grupos grupos = this.repositorios.getGrupos();
		grupos.salvar(grupo);
		
		

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Grupo grupo){
		Grupos grupos = this.repositorios.getGrupos();
		grupos.editar(grupo);
	}

	public void excluir(Grupo grupo){
		Grupos grupos = this.repositorios.getGrupos();
		grupos.remover(grupo);
		this.init();
	}

	
	
	public Grupo getGrupo() {
		return grupo;
	}


	public void setGrupo(Grupo grupo) throws CloneNotSupportedException {
		this.grupo = grupo;
		if (this.grupo == null) {
			this.grupo = new Grupo();
		}else{
			this.grupo = (Grupo) grupo.clone();
		}
	}


	public List<Grupo> getGrupos() {
		return grupos;
	}


	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}
}
