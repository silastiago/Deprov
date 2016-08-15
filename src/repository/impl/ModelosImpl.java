package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Modelo;
import repository.Modelos;


public class ModelosImpl implements Modelos{
	private Session sessao;

	public ModelosImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Modelo> listar(){
		return sessao.createCriteria(Modelo.class).addOrder(Order.asc("modelo")).list();
	}

	@Override
	public Modelo porCodigo(Integer codigo) {
		return (Modelo) sessao.get(Modelo.class, codigo);
	}

	@Override
	public void salvar(Modelo modelo) {
		this.sessao.merge(modelo);
	}

	@Override
	public void remover(Modelo modelo) {
		this.sessao.delete(modelo);

	}
}