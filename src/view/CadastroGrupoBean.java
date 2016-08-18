package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
	private List<Grupo> listaGrupos = new ArrayList<Grupo>();

	public void cadastrar(){
		Grupos grupos = this.repositorios.getGrupos();
		grupos.salvar(grupo);
		this.grupo = new Grupo();
	}

	public void excluir(Grupo grupo){
		Grupos grupos = this.repositorios.getGrupos();
		grupos.remover(grupo);
		this.listarGrupos();
	}
	
	public List<Grupo> listarGrupos(){
		Grupos grupos = this.repositorios.getGrupos();
		listaGrupos = grupos.listar();
		return listaGrupos;
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

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}
}