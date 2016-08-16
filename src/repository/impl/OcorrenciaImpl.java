package repository.impl;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Ocorrencia;
import repository.Ocorrencias;

/** Esta � uma Classe concreta que implementa a Interface Ocorrencias,
*   
* @author silas
* @since 16-08-2016
*/
public class OcorrenciaImpl implements Ocorrencias{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao ser� a sessao que o hibernate cria para conexoes com o banco.
     */
	public OcorrenciaImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo pesquisa uma ocorrencia por seu id.
	*  	
	*  @param codigo, Este codigo � o id da ocorrencia que voc� est� procurando.
	*  @return retorna a Ocorrencia daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Ocorrencias.	
	*/
	@Override
	public Ocorrencia porCodigo(Integer codigo) {
		return (Ocorrencia) sessao.get(Ocorrencia.class, codigo);
	}

	/** Este metodo cria ou altera uma ocorrencia.
	*  	
	*  @param ocorrencia, Este ocorrencia � o objeto Ocorrencia que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Ocorrencias.
	*/
	@Override
	public void salvar(Ocorrencia ocorrencia) {
		this.sessao.merge(ocorrencia);
	}

	/** Este metodo Remove uma ocorrencia.
	*  	
	*  @param ocorrencia, Esta ocorrencia � o objeto Fabricante que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Ocorrencias.
	*/
	@Override
	public void remover(Ocorrencia ocorrencia) {
		this.sessao.delete(ocorrencia);

	}

	/** Este metodo lista todas as ocorrencias de um determinado veiculo.
	*  	
	*  @param codigo_veiculo, Este codigo_veiculo � o id do veiculo que voc� est� procurando.
	*  @return retorna as Ocorrencias daquele veiculo que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Ocorrencias.	
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> porCodigoVeiculo(Integer codigo_veiculo) {
		return sessao.createCriteria(Ocorrencia.class).add(Restrictions.eq("veiculo.codigo", codigo_veiculo)).addOrder(Order.asc("data")).list();
	}
}