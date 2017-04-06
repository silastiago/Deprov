package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IPericia;
import pcrn.model.Pericia;

/** Esta � uma Classe concreta que implementa a Interface Pericias,
*   
* @author silas
* @since 16-08-2016
*/
public class Pericias implements IPericia, Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	

	/** Este metodo lista os exames veiculares cadastrados.
	* 	@return retorna a lista dos exames veiculares cadastrados.
	* 	Este metodo sobrescreve o da interface Pericia.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Pericia> listar() {
		
		List<Pericia> listaPericias = new ArrayList<Pericia>();
		Query query = manager.createQuery("Select p from Pericia p order by pericia asc");
		listaPericias = query.getResultList();
		
		return listaPericias;
	}

	/** Este metodo pesquisa um exame veicular por seu id.
	*  	
	*  @param codigo, Este codigo � o id do exame veicular que voc� est� procurando.
	*  @return retorna o Pericia daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Pericias.	
	*/
	@Override
	public Pericia porCodigo(Integer codigo) {
		return manager.find(Pericia.class, codigo);
	}

	/** Este metodo cria ou altera uma pericia.
	*  	
	*  @param pericia, Este pericia � o objeto Pericia que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Pericias.
	*/
	@Override
	public void salvar(Pericia pericia) {
		manager.persist(pericia);
	}

	/** Este metodo Remove uma pericia.
	*  	
	*  @param pericia, Esta pericia � o objeto Pericia que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Pericias.
	*/
	@Override
	public void remover(Pericia pericia) {
		manager.remove(pericia);
	}

	@Override
	public Pericia pegaCodigo(String periciaBusca) {
		
		Query query = manager.createQuery("from Pericia where pericia = :pericia");
		query.setParameter("pericia", periciaBusca);
		Pericia pericia = (Pericia) query.getSingleResult();
		
		return pericia;
	}
}