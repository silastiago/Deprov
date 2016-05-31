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
import model.Pericia;
import repository.Cores;
import repository.Pericias;
import util.Repositorios;

@ManagedBean(name="cadastroPericiaBean")
@RequestScoped
public class CadastroPericiaBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Pericia pericia = new Pericia();	
	private List<Pericia> pericias = new ArrayList<Pericia>();
	

	@PostConstruct
	public void init(){
		Pericias pericias = this.repositorios.getPericias();
		this.pericias = pericias.listar();
		
	}


	public void cadastrar(){
		Pericias pericias = this.repositorios.getPericias();
		pericias.salvar(pericia);

		String msg = "Cadastro efetuado com sucesso!";
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public void update(Pericia pericia){
		Pericias pericias = this.repositorios.getPericias();
		pericias.editar(pericia);
	}

	public void excluir(Pericia pericia){
		Pericias pericias = this.repositorios.getPericias();
		pericias.remover(pericia);
		this.init();
	}


	public Pericia getPericia() {
		return pericia;
	}


	public void setPericia(Pericia pericia) throws CloneNotSupportedException {
		this.pericia = pericia;
		if (this.pericia == null) {
			this.pericia = new Pericia();
		}else{
			this.pericia = (Pericia) pericia.clone();
		}
	}


	public List<Pericia> getPericias() {
		return pericias;
	}


	public void setPericias(List<Pericia> pericias) {
		this.pericias = pericias;
	}

	
}
