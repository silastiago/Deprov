package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import model.Cor;
import model.Fabricante;
import repository.Cores;
import repository.Fabricantes;
import util.Repositorios;

@ManagedBean(name="cadastroCorBean")
@RequestScoped
public class CadastroCorBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Cor cor = new Cor();	
	private List<Cor> cores = new ArrayList<Cor>();

	@PostConstruct
	public void init(){
		Cores cores = this.repositorios.getCores();
		this.cores = cores.listar();
	}


	public void cadastrar(){
		Cores cores = this.repositorios.getCores();
		cores.salvar(cor);
		this.cor = new Cor();

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Cor cor){
		Cores cores = this.repositorios.getCores();
		cores.editar(cor);
	}

	public void excluir(Cor cor){
		Cores cores = this.repositorios.getCores();
		cores.remover(cor);
		this.init();
	}

	public Cor getCor() {
		return cor;
	}

	
	public void setCor(Cor cor) throws CloneNotSupportedException {
		this.cor = cor;
		if (this.cor == null) {
			this.cor = new Cor();
		}else {
			this.cor = (Cor) cor.clone();
		}
	}

	public List<Cor> getCores() {
		return cores;
	}


	public void setCores(List<Cor> cores) {
		this.cores = cores;
	}
}
