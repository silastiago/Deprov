package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

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
		this.grupo = new Grupo();
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
