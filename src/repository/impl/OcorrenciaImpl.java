package repository.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Ocorrencia;
import repository.Ocorrencias;


public class OcorrenciaImpl implements Ocorrencias{
	private Session sessao;

	public OcorrenciaImpl(Session sessao){
		this.sessao = sessao;
	}

	@Override
	public Ocorrencia porCodigo(Integer codigo) {
		return (Ocorrencia) sessao.get(Ocorrencia.class, codigo);
	}

	@Override
	public void salvar(Ocorrencia ocorrencia) {
		this.sessao.merge(ocorrencia);
	}

	@Override
	public void remover(Ocorrencia ocorrencia) {
		this.sessao.delete(ocorrencia);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> porCodigoVeiculo(Integer codigo_veiculo) {
		return sessao.createCriteria(Ocorrencia.class).add(Restrictions.eq("veiculo.codigo", codigo_veiculo)).addOrder(Order.asc("data")).list();
	}
}