package view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;


import model.Pessoa;
import repository.impl.PessoasImpl;

@ManagedBean
@SessionScoped
public class LoginBean {

	private String usuario;
	private String senha;

	public String logar() {
		try {
			this.getRequest().login(this.usuario, this.senha);
			getRequest().getSession().setAttribute("login", this.usuario);
			getRequest().getSession().setAttribute("senha", this.senha);
			return "site/index?faces-redirect=true";
		} catch (ServletException e) {
			return "Login?faces-redirect=true";
		}
	}

	public String sair() throws ServletException {
		this.getRequest().logout();
		getRequest().getSession().invalidate();
		return "../Login?faces-redirect=true";
	}

	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}

	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}