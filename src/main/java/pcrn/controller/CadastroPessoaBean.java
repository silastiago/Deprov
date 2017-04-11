package pcrn.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import pcrn.model.Pessoa;
import pcrn.security.UsuarioSistema;
import pcrn.services.PessoaService;
import pcrn.util.FacesUtil;


@Named
@RequestScoped
public class CadastroPessoaBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private PessoaService pessoaService;
	
	private Pessoa pessoa = new Pessoa();
	private List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
	
	public void cadastrar(){
		String senha = this.pessoa.getSenha();
		pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);
		FacesUtil.addInfoMessage("Pessoa cadastrada com sucesso");
	}

	public void alterarSenha(){
		
		String senha = this.pessoa.getSenha();
		pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);
		
		FacesUtil.addInfoMessage("Senha alterada com sucesso");
		
	}
	
	public void editar(){
		
		pessoaService.salvar(pessoa);
		FacesUtil.addInfoMessage("pessoa alterada com sucesso");
	}
	
	public void alterarPropriaSenha(){
		
		String senha = this.pessoa.getSenha();
		pessoa = this.getUsuarioLogado().getPessoa();
		this.pessoa.setSenha(FacesUtil.md5(senha));
		pessoaService.salvar(pessoa);	
		FacesUtil.addInfoMessage("Senha alterada com sucesso");
		
	}	
	
	public void excluir(Pessoa pessoa){
		pessoaService.remover(pessoa);
		FacesUtil.addInfoMessage("Pessoa: " +pessoa.getLogin()+ " removida com sucesso");
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
