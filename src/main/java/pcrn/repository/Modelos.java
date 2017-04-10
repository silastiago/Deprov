package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IModelo;
import pcrn.model.Modelo;

/** Esta � uma Classe concreta que implementa a Interface Modelos,
*   
* @author silas
* @since 16-08-2016
*/
public class Modelos implements IModelo, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	

	/** Este metodo lista os modelos cadastrados.
	* 	@return retorna a lista dos modelos cadastrados em ordem ascendente.
	* 	Este metodo sobrescreve o da interface Modelos.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Modelo> listar(){
		
		List<Modelo> listaModelos = new ArrayList<Modelo>();
		Query query = manager.createQuery("Select m from Modelo m order by modelo asc");
		listaModelos = query.getResultList();
		
		return listaModelos;
	}

	/** Este metodo pesquisa um modelo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do modelo que voc� est� procurando.
	*  @return retorna o Modelo daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Modelos.	
	*/
	@Override
	public Modelo porCodigo(Integer codigo) {
		return manager.find(Modelo.class, codigo);
	}

	/** Este metodo cria ou altera um modelo.
	*  	
	*  @param modelo, Este modelo � o objeto Modelo que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Modelos.
	*/
	@Override
	public void salvar(Modelo modelo) {
		manager.merge(modelo);
	}

	/** Este metodo Remove um modelo.
	*  	
	*  @param modelo, Esta modelo � o objeto Modelo que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Modelos.
	*/
	@Override
	public void remover(Modelo modelo) {
		Modelo modeloTemporario = manager.find(Modelo.class, modelo.getCodigo());
		manager.remove(modeloTemporario);
	}
	
	/** Este metodo pesquisa um modelo pelo seu nome.
	*  	
	*  @param modelo, Este modelo � o nome do modelo que voc� est� procurando.
	*  @return retorna o modelo daquele nome que voc� est� pesquisando.
	*   	
	*/
	@Override
	public Modelo pegaCodigo(String modeloBusca) {
		
		Query query = manager.createQuery("from Modelo where modelo = :modelo");
		query.setParameter("modelo", modeloBusca);
		Modelo modelo = (Modelo) query.getSingleResult();
		
		return modelo;
	}
	
	/** Este metodo lista todos os modelos de um determinado fabricante.
	*  	
	*  @param fabricante, Este fabricante � o id do fabricante que voc� est� pesquisando.
	*  @return retorna uma lista de modelos daquele fabricante que voc� est� pesquisando.
	*   	
	*/
	@Override
	public List<Modelo> pegaModelos(int codigo_fabricante) {
		
		List<Modelo> listaModelos = new ArrayList<Modelo>();
		Query query = manager.createQuery("from Local where codigo_fabricante = :codigo_fabricante");
		query.setParameter("codigo_fabricante", codigo_fabricante);
		listaModelos = query.getResultList();
		
		return listaModelos;
	}
}