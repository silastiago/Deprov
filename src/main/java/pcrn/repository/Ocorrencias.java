package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import pcrn.interfaces.IOcorrencia;
import pcrn.model.Modelo;
import pcrn.model.Ocorrencia;

/** Esta � uma Classe concreta que implementa a Interface Ocorrencias,
*   
* @author silas
* @since 16-08-2016
*/
public class Ocorrencias implements IOcorrencia, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/** Este metodo pesquisa uma ocorrencia por seu id.
	*  	
	*  @param codigo, Este codigo � o id da ocorrencia que voc� est� procurando.
	*  @return retorna a Ocorrencia daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Ocorrencias.	
	*/
	@Override
	public Ocorrencia porCodigo(int codigo) {
		return manager.find(Ocorrencia.class, codigo);
	}

	/** Este metodo cria ou altera uma ocorrencia.
	*  	
	*  @param ocorrencia, Este ocorrencia � o objeto Ocorrencia que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Ocorrencias.
	*/
	@Override
	public void salvar(Ocorrencia ocorrencia) {
		manager.persist(ocorrencia);
	}

	/** Este metodo Remove uma ocorrencia.
	*  	
	*  @param ocorrencia, Esta ocorrencia � o objeto Fabricante que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Ocorrencias.
	*/
	@Override
	public void remover(Ocorrencia ocorrencia) {
		manager.remove(ocorrencia);

	}

	/** Este metodo lista todas as ocorrencias de um determinado veiculo.
	*  	
	*  @param codigo_veiculo, Este codigo_veiculo � o id do veiculo que voc� est� procurando.
	*  @return retorna as Ocorrencias daquele veiculo que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Ocorrencias.	
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Ocorrencia> porCodigoVeiculo(int codigoVeiculo) {
		
		List<Ocorrencia> listaOcorrencias = new ArrayList<Ocorrencia>();
		Query query = manager.createQuery("from Ocorrencia where codigo_veiculo = :codigo_veiculo order by data asc");
		query.setParameter("codigo_veiculo", codigoVeiculo);
		listaOcorrencias = query.getResultList();
		
		
		return listaOcorrencias;
	}
}