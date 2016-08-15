package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import model.Grupo;
import repository.Grupos;

public class GruposImpl implements Grupos{
	private Session sessao;

	public GruposImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Grupo> listar() {
		return sessao.createCriteria(Grupo.class).addOrder(Order.asc("grupo")).list();
	}

	@Override
	public Grupo porCodigo(Integer codigo) {
		return (Grupo) sessao.get(Grupo.class, codigo);
	}

	@Override
	public void salvar(Grupo grupo) {
		this.sessao.merge(grupo);
	}

	@Override
	public void remover(Grupo grupo) {
		this.sessao.delete(grupo);
	}
}