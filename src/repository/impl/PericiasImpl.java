package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import model.Pericia;
import repository.Pericias;

public class PericiasImpl implements Pericias{
	private Session sessao;

	public PericiasImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pericia> listar() {
		return sessao.createCriteria(Pericia.class).addOrder(Order.asc("pericia")).list();
	}

	@Override
	public Pericia porCodigo(Integer codigo) {
		return (Pericia) sessao.get(Pericia.class, codigo);
	}

	@Override
	public void salvar(Pericia pericia) {
		this.sessao.merge(pericia);
	}

	@Override
	public void remover(Pericia pericia) {
		this.sessao.delete(pericia);

	}
}