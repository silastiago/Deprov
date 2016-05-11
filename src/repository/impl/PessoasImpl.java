package repository.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.criterion.Order;


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
	public Pessoa salvar(Pessoa pessoa) {
		return (Pessoa) sessao.merge(pessoa);
	}

	@Override
	public void remover(Pessoa pessoa) {
		this.sessao.delete(pessoa);
	}

	@Override
	public void editar(Pessoa pessoa) {
		this.sessao.update(pessoa);
	}
}