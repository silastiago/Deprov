package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Fabricante;
import model.Tipo;
import repository.Tipos;

public class TiposImpl implements Tipos{
	private Session sessao;

	public TiposImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tipo> listar() {
		return sessao.createCriteria(Tipo.class).addOrder(Order.asc("tipo")).list();
	}

	@Override
	public Tipo porCodigo(Integer codigo) {
		return (Tipo) sessao.get(Tipo.class, codigo);
	}

	@Override
	public void salvar(Tipo tipo) {
		this.sessao.merge(tipo);
	}

	@Override
	public void remover(Tipo tipo) {
		this.sessao.delete(tipo);
	}
}