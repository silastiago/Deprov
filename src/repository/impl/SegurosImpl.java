package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Pericia;
import model.Seguro;
import repository.Seguros;

public class SegurosImpl implements Seguros{
	private Session sessao;

	public SegurosImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Seguro> listar() {
		return sessao.createCriteria(Seguro.class).addOrder(Order.asc("seguro")).list();
	}

	@Override
	public Seguro porCodigo(Integer codigo) {
		return (Seguro) sessao.get(Seguro.class, codigo);
	}

	@Override
	public Seguro salvar(Seguro seguro) {
		return (Seguro) sessao.merge(seguro);
	}

	@Override
	public void remover(Seguro seguro) {
		this.sessao.delete(seguro);

	}

	@Override
	public void editar(Seguro seguro) {
		this.sessao.update(seguro);
	}
}