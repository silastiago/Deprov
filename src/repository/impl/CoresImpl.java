package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Cor;
import repository.Cores;

public class CoresImpl implements Cores{
	private Session sessao;

	public CoresImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cor> listar() {
		return sessao.createCriteria(Cor.class).addOrder(Order.asc("cor")).list();
	}

	@Override
	public Cor porCodigo(Integer codigo) {
		return (Cor) sessao.get(Cor.class, codigo);
	}

	@Override
	public Cor salvar(Cor cor) {
		return (Cor) sessao.merge(cor);
	}

	@Override
	public void remover(Cor cor) {
		this.sessao.delete(cor);

	}

	@Override
	public void editar(Cor cor) {
		this.sessao.update(cor);
	}
}