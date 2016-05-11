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
import model.Pessoa;
import repository.Grupos;
import repository.Pessoas;
import util.Repositorios;

@ManagedBean(name="cadastroPessoaBean")
@RequestScoped
public class CadastroPessoaBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> pessoas = new ArrayList<Pessoa>();
	private List<Grupo> grupos = new ArrayList<Grupo>();

	@PostConstruct
	public void init(){
		Pessoas pessoas = this.repositorios.getPessoas();
		Grupos grupos = this.repositorios.getGrupos();
		this.pessoas = pessoas.listar();
		this.grupos = grupos.listar();
	}

	public void cadastrar(){
		Pessoas pessoas = this.repositorios.getPessoas();
		pessoas.salvar(this.pessoa);

		this.pessoa = new Pessoa();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}


	
	public void update(Pessoa pessoa){
		Pessoas pessoas = this.repositorios.getPessoas();
		pessoas.editar(pessoa);
	}

	public void excluir(Pessoa pessoa){
		Pessoas pessoas = this.repositorios.getPessoas();
		pessoas.remover(pessoa);
		this.init();
	}

	
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) throws CloneNotSupportedException {
		this.pessoa = pessoa;
		if (this.pessoa == null) {
			this.pessoa = new Pessoa();
		}else {
			this.pessoa = (Pessoa) pessoa.clone();
		}
	}

	public List<Pessoa> getPessoas(){
		return pessoas;
	}


	public List<Grupo> getGrupos() {
		return grupos;
	}


	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}


	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
	
}
