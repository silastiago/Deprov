package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import pcrn.model.Pessoa;
import pcrn.security.UsuarioSistema;
import pcrn.services.PessoaService;
import pcrn.util.FacesUtil;


@Named
@ViewScoped
public class CadastroPessoaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@Inject
	private PessoaService pessoaService;
	
	private Pessoa pessoa = new Pessoa();
	private Pessoa pessoaSelecionada;
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	
	public String cadastrar(){
		
		String senha = this.pessoa.getSenha();
		pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);
		
		String pagina = "/site/Pessoa/Consulta/ListarPessoas.xhtml?faces-redirect=true";
		FacesUtil.addInfoMessage("Pessoa cadastrada com sucesso");		
		FacesUtil.contextFlash();
		
		return pagina;
		
	}

	public String alterarSenha(){
		
		String senha = this.pessoa.getSenha();
		pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);
		String pagina = "/site/Pessoa/Consulta/ListarPessoas.xhtml?faces-redirect=true";
		
		FacesUtil.addInfoMessage("Senha alterada com sucesso");
		FacesUtil.contextFlash();
		
		return pagina;
		
	}
	
	public String editar(){
		
		pessoaService.salvar(pessoa);
		FacesUtil.addInfoMessage("Pessoa alterada com sucesso");
		FacesUtil.contextFlash();
		
		String pagina = "/site/Pessoa/Consulta/ListarPessoas.xhtml?faces-redirect=true";
		
		return pagina;
		
	}
	
	public void alterarPropriaSenha(){
		
		String senha = this.pessoa.getSenha();
		pessoa = this.getUsuarioLogado().getPessoa();
		this.pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);	
		FacesUtil.addInfoMessage("Senha alterada com sucesso");
		
	}	
	
	public void excluir(){
		
		pessoaService.remover(pessoaSelecionada);
		FacesUtil.addInfoMessage("Pessoa: " + pessoaSelecionada.getLogin()+ " removida com sucesso");
	}
	
	public List<Pessoa> listarPessoas(){
		listaPessoas = pessoaService.listar();
		return listaPessoas;
	}
	
	private UsuarioSistema getUsuarioLogado() {
		UsuarioSistema usuario = null;
		
		UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) 
				FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		
		if (auth != null && auth.getPrincipal() != null) {
			usuario = (UsuarioSistema) auth.getPrincipal();
		}
		
		return usuario;
	} 
	
	public String novo(){
		
		String pagina = "/site/Pessoa/Novo/Pessoa.xhtml?faces-redirect=true";
		
		return pagina;
	}
	
	public String edicao(){
		
		String pagina = "/site/Pessoa/Edicao/Pessoa.xhtml?codigo="+pessoaSelecionada.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	public String alteracaoSenha(){
		
		String pagina = "/site/Pessoa/Edicao/PessoaSenha.xhtml?codigo="+pessoaSelecionada.getCodigo()+"faces-redirect=true";
		
		return pagina;
	}
	
	
	public Pessoa getPessoaSelecionada() {
		return pessoaSelecionada;
	}

	public void setPessoaSelecionada(Pessoa pessoaSelecionada) {
		this.pessoaSelecionada = pessoaSelecionada;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListaPessoas() {
		return listaPessoas;
	}

	public void setListaPessoas(List<Pessoa> listaPessoas) {
		this.listaPessoas = listaPessoas;
	}

}
