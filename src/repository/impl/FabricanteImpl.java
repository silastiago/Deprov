package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Fabricante;
import repository.Fabricantes;

public class FabricanteImpl implements Fabricantes{
	private Session sessao;

	public FabricanteImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fabricante> listar() {
		return sessao.createCriteria(Fabricante.class).addOrder(Order.asc("fabricante")).list();
	}

	@Override
	public Fabricante porCodigo(Integer codigo) {
		return (Fabricante) sessao.get(Fabricante.class, codigo);
	}

	@Override
	public void salvar(Fabricante fabricante) {
		this.sessao.merge(fabricante);
	}

	@Override
	public void remover(Fabricante fabricante) {
		this.sessao.delete(fabricante);
	}
}