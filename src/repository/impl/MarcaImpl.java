package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Marca;
import repository.Marcas;

public class MarcaImpl implements Marcas{
	private Session sessao;

	public MarcaImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Marca> listar() {
		return sessao.createCriteria(Marca.class).addOrder(Order.asc("marca")).list();
	}

	@Override
	public Marca porCodigo(Integer codigo) {
		return (Marca) sessao.get(Marca.class, codigo);
	}

	@Override
	public Marca salvar(Marca fabricante) {
		return (Marca) sessao.merge(fabricante);
	}

	@Override
	public void remover(Marca fabricante) {
		this.sessao.delete(fabricante);

	}

	@Override
	public void editar(Marca fabricante) {
		this.sessao.update(fabricante);
	}
}