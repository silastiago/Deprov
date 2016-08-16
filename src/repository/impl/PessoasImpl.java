package repository.impl;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Pessoa;
import repository.Pessoas;

public class PessoasImpl implements Pessoas{
	private Session sessao;

	public PessoasImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listar() {
		return sessao.createCriteria(Pessoa.class).addOrder(Order.asc("login")).list();
	}

	@Override
	public Pessoa porCodigo(Integer codigo) {
		return (Pessoa) sessao.get(Pessoa.class, codigo);
	}

	@Override
	public void salvar(Pessoa pessoa) {
		this.sessao.merge(pessoa);
	}

	@Override
	public void remover(Pessoa pessoa) {
		this.sessao.delete(pessoa);
	}
	
	
	@Override
	public boolean login(Pessoa pessoa) {
		boolean pessoaExistente = true;
		Criteria c = this.sessao.createCriteria(Pessoa.class);
		c.add(Restrictions.eq("login", pessoa.getLogin()));
		c.add(Restrictions.eq("senha", pessoa.getSenha()));
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.setAttribute("usuario", pessoa.getLogin());
		session.setAttribute("senha", pessoa.getSenha());
		if (c.uniqueResult() == null) {
			pessoaExistente = false;
		}else{
			pessoaExistente = true;
		}
		
		return pessoaExistente;
	}

	@Override
	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.removeAttribute("usuario");
		session.removeAttribute("senha");
		session.invalidate();
		
	}
}