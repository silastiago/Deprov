package repository.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Veiculo;
import repository.Veiculos;


public class VeiculosImpl implements Veiculos{
	private Session sessao;

	public VeiculosImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Veiculo> listar(){
		return sessao.createCriteria(Veiculo.class).addOrder(Order.asc("placa")).list();
	}

	@Override
	public Veiculo porCodigo(Integer codigo) {
		return (Veiculo) sessao.get(Veiculo.class, codigo);
	}

	@Override
	public Veiculo salvar(Veiculo veiculo) {
		return (Veiculo) sessao.merge(veiculo);
	}

	@Override
	public void remover(Veiculo veiculo) {
		this.sessao.delete(veiculo);

	}

	@Override
	public void editar(Veiculo veiculo) {
		this.sessao.update(veiculo);
	}

	@Override
	public List<Veiculo> listarPorPlaca(Veiculo veiculo) {
		//Criteria criteria = sessao.createCriteria(Veiculo.class).addQueryHint("FROM Veiculo E WHERE E.Placa = jfoerhgoh");
		return sessao. createCriteria(Veiculo.class).add(Restrictions.eq("Placa", veiculo.getPlaca())).list();
	}
}