package repository.impl;

import java.util.List;
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
		return sessao.createCriteria(Veiculo.class).addOrder(Order.desc("dossie")).list();
	}

	@Override
	public Veiculo porCodigo(Integer codigo) {
		return (Veiculo) sessao.get(Veiculo.class, codigo);
	}

	@Override
	public void salvar(Veiculo veiculo) {
		this.sessao.merge(veiculo);
	}

	@Override
	public void remover(Veiculo veiculo) {
		this.sessao.delete(veiculo);

	}

	@Override
	public List<Veiculo> listarPorPlaca(int codigo) {
		//Criteria criteria = sessao.createCriteria(Veiculo.class).addQueryHint("FROM Veiculo E WHERE E.Placa = jfoerhgoh");
		return sessao.createCriteria(Veiculo.class).add(Restrictions.eq("veiculo.placa", codigo)).list();
	}
}