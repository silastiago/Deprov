package view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Pessoa;
import repository.Pessoas;
import util.Repositorios;

@ManagedBean(name="PessoaBean")
@RequestScoped
public class CadastroPessoaBean implements Serializable{

	private Repositorios repositorios = new Repositorios();
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	//private List<Grupo> grupos = new ArrayList<Grupo>();

	/*@PostConstruct
	public void init(){
		Pessoas pessoas = this.repositorios.getPessoas();
		this.pessoas = pessoas.listar();
	}*/

	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}
	
	public void cadastrar(){
		Pessoas pessoas = this.repositorios.getPessoas();
		pessoas.salvar(this.pessoa);
	}

	public void excluir(Pessoa pessoa){
		Pessoas pessoas = this.repositorios.getPessoas();
		pessoas.remover(pessoa);
		this.listarPessoas();
	}

	public String logar(){
		Pessoas pessoas = this.repositorios.getPessoas();
		System.out.println(pessoas.login(pessoa));
		String pagina = "";
		if (pessoas.login(pessoa) == false) {
			pagina = "Login.xhtml?faces-redirect=true";
		}else{
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("usuario", pessoa.getLogin());
			session.setAttribute("senha", pessoa.getSenha());
			
			pagina = "site/index.xhtml?faces-redirect=true";
		}
		return pagina;
	}

	public String sair() {
		System.out.println("TESTANDO FUNCAO DE SAIR");
		Pessoas pessoas = this.repositorios.getPessoas();
		pessoas.logout(); 
		return "../Login.xhtml?faces-redirect=true";
	}	
	
	public List<Pessoa> listarPessoas(){
		//Esta linha estou instanciando a interface com sua implementacao.
		Pessoas pessoas = this.repositorios.getPessoas();
		//Esta linha lista os tipos e joga em uma lista de tipos.
		listaPessoas = pessoas.listar();
		//retorna a lista de tipos.
		return listaPessoas;
	}
	
	public boolean visualizar_cadastro(){
		Pessoa pessoa = new Pessoa();
		String login = this.getRequest().getSession().getAttribute("usuario").toString();
		Pessoas pessoas = this.repositorios.getPessoas();
		pessoa = pessoas.retornaPessoa(login);
		
		System.out.println("Grupo: "+ pessoa.getGrupo().getGrupo().toUpperCase() + " do usuario: "+ pessoa.getLogin().toUpperCase());
		if(pessoa.getGrupo().getGrupo().toUpperCase().equals("CONSULTA")){
			return true;
		}else{
			return false;
		}	
	}
	
	public boolean visualizar_consulta(Pessoa pessoa){
		if(pessoa.getGrupo().getGrupo().toUpperCase().equals("")){
			return true;
		}else{
			return false;
		}	
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

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}
}