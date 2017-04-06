package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.ITipo;
import pcrn.model.Tipo;


/** Esta � uma Classe concreta que implementa a Interface Tipos,
*   
* @author silas
* @since 16-08-2016
*/
public class Tipos implements ITipo, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	/** Este metodo lista os tipos de veiculos cadastrados.
	* 	@return retorna a lista dos tipos de veiculos cadastrados.
	* 	Este metodo sobrescreve o da interface Tipos.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Tipo> listar() {
		
		List<Tipo> listaTipos = new ArrayList<Tipo>();
		Query query = manager.createQuery("Select t from Tipo t order by tipo asc");
		listaTipos = query.getResultList();
		
		return listaTipos;
	}

	/** Este metodo pesquisa um tipo de veiculo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do tipo que voc� est� procurando.
	*  @return retorna o Tipo daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Tipos.	
	*/
	@Override
	public Tipo porCodigo(Integer codigo) {
		return manager.find(Tipo.class, codigo);
	}

	/** Este metodo cria ou altera um tipo.
	*  	
	*  @param tipo, Este tipo � o objeto Tipo que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Tipos.
	*/
	@Override
	public void salvar(Tipo tipo) {
		manager.persist(tipo);
	}

	/** Este metodo Remove um tipo.
	*  	
	*  @param tipo, Esta tipo � o objeto Tipo que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Tipos.
	*/
	@Override
	public void remover(Tipo tipo) {
		manager.remove(tipo);
	}
}