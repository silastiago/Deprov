package repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Cor;
import model.Ocorrencia;
import model.Veiculo;
import repository.Ocorrencias;


public class OcorrenciaImpl implements Ocorrencias{
	private Session sessao;

	public OcorrenciaImpl(Session sessao){
		this.sessao = sessao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> listar() {
		return sessao.createCriteria(Ocorrencia.class).addOrder(Order.asc("nome")).list();
	}

	@Override
	public Ocorrencia porCodigo(Integer codigo) {
		return (Ocorrencia) sessao.get(Ocorrencia.class, codigo);
	}

	@Override
	public Ocorrencia salvar(Ocorrencia ocorrencia) {
		return (Ocorrencia) sessao.merge(ocorrencia);
	}

	@Override
	public void remover(Ocorrencia ocorrencia) {
		this.sessao.delete(ocorrencia);

	}

	@Override
	public void editar(Ocorrencia ocorrencia) {
		this.sessao.update(ocorrencia);
	}

	@Override
	public List<Ocorrencia> porCodigoVeiculo(Integer codigo_veiculo) {
		return sessao.createCriteria(Ocorrencia.class).add(Restrictions.eq("veiculo.codigo", codigo_veiculo)).list();
	}
}