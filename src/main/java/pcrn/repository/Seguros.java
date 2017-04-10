package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.ISeguro;
import pcrn.model.Seguro;




/** Esta � uma Classe concreta que implementa a Interface Seguros,
*   
* @author silas
* @since 16-08-2016
*/
public class Seguros implements ISeguro, Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/** Este metodo lista os seguros cadastrados.
	* 	@return retorna a lista dos seguros cadastrados.
	* 	Este metodo sobrescreve o da interface Seguros.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Seguro> listar() {
		
		List<Seguro> listaSeguros = new ArrayList<Seguro>();
		Query query = manager.createQuery("Select s from Seguro s order by seguro asc");
		listaSeguros = query.getResultList();
		
		return listaSeguros;
	}

	/** Este metodo pesquisa um seguro por seu id.
	*  	
	*  @param codigo, Este codigo � o id do seguro que voc� est� procurando.
	*  @return retorna o Seguro daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Seguros.	
	*/
	@Override
	public Seguro porCodigo(Integer codigo) {
		return manager.find(Seguro.class, codigo);
	}

	/** Este metodo cria ou altera um seguro.
	*  	
	*  @param seguro, Este seguro � o objeto Seguro que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Seguros.
	*/
	@Override
	public void salvar(Seguro seguro) {
		manager.merge(seguro);
	}

	/** Este metodo Remove um seguro.
	*  	
	*  @param seguro, Esta seguro � o objeto Seguro que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Seguros.
	*/
	@Override
	public void remover(Seguro seguro) {
		Seguro seguroTemporario = manager.find(Seguro.class, seguro.getCodigo());
		manager.remove(seguroTemporario);
	}
	
	/** Este metodo pesquisa um seguro pelo seu nome.
	*  	
	*  @param seguro, Este seguro � o nome do seguro que voc� est� procurando.
	*  @return retorna o seguro daquele nome que voc� est� pesquisando.
	*   	
	*/
	@Override
	public Seguro pegaCodigo(String seguroBusca) {
		
		Query query = manager.createQuery("from Seguro where seguro = :seguro");
		query.setParameter("seguro", seguroBusca);
		Seguro seguro = (Seguro) query.getSingleResult();
		
		return seguro;
	}
}