package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.ILocal;
import pcrn.model.Local;

/** Esta � uma Classe concreta que implementa a Interface Cores,
*   
* @author silas
* @since 15-08-2016
*/

public class Locais implements ILocal, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de cores cadastradas.
	* 	Este metodo sobrescreve o da interface Cores.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Local> listar() {
		List<Local> listaLocais = new ArrayList<Local>();
		Query query = manager.createQuery("Select l from Local l order by local asc");
		listaLocais = query.getResultList();
		return listaLocais;
	}

	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo � o id da cor que voc� est� procurando.
	*  @return Cor, retorna a Cor daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Cores.	
	*/
	@Override
	public Local porCodigo(Integer codigo) {
		return manager.find(Local.class, codigo);
	}

	/** Este metodo cria ou altera uma cor.
	*  	
	*  @param cor, Esta cor � o objeto Cor que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void salvar(Local local) {
		manager.merge(local);
	}

	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor � o objeto cor que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void remover(Local local) {
		Local localTemporario = manager.find(Local.class, local.getCodigo());
		manager.remove(localTemporario);
	}
	
	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor � o nome do corque voc� est� procurando.
	*  @return retorna o cor daquele nome que voc� est� pesquisando.
	*   	
	*/
	@Override
	public Local pegaCodigo(String localBusca) {
		Query query = manager.createQuery("from Local where local = :local");
		query.setParameter("local", localBusca);
		Local local = (Local) query.getSingleResult();
		return local;
	}
}