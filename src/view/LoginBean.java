package view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.DispatcherType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Pessoa;
import repository.impl.PessoasImpl;

@ManagedBean(name="loginBean")
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

	public String sair() throws ServletException, IOException {
		System.out.println("TESTANDO METODO DE SAIR ");
		this.getRequest().logout();
		this.getRequest().getSession().invalidate();
		
		//this.getResponse().sendRedirect("http://snmp.info.ufrn.br:8080/Deprov/Login?faces-redirect=true");
		return "../Login?faces-redirect=true";
	}

	private HttpServletRequest getRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletRequest) context.getExternalContext().getRequest();
	}
	
	private HttpServletResponse getResponse() {
		FacesContext context = FacesContext.getCurrentInstance();
		return (HttpServletResponse) context.getExternalContext().getResponse();
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