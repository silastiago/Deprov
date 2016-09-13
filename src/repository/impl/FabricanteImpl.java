package repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Fabricante;
import repository.Fabricantes;

/** Esta é uma Classe concreta que implementa a Interface Fabricantes,
*   
* @author silas
* @since 16-08-2016
*/

public class FabricanteImpl implements Fabricantes{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao será a sessao que o hibernate cria para conexoes com o banco.
     */
	public FabricanteImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista os fabricantes cadastrados.
	* 	@return retorna a lista dos fabricantes cadastrados em ordem ascendente.
	* 	Este metodo sobrescreve o da interface Fabricantes.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Fabricante> listar() {
		return sessao.createCriteria(Fabricante.class).addOrder(Order.asc("fabricante")).list();
	}

	/** Este metodo pesquisa um fabricante por seu id.
	*  	
	*  @param codigo, Este codigo é o id do fabricante que você está procurando.
	*  @return retorna o Fabrincante daquele id que você está pesquisando.
	*  Este metodo sobrescreve o da interface Fabricantes.	
	*/
	@Override
	public Fabricante porCodigo(Integer codigo) {
		return (Fabricante) sessao.get(Fabricante.class, codigo);
	}

	/** Este metodo cria ou altera um fabricante.
	*  	
	*  @param fabricante, Este fabricante é o objeto Fabricante que você irá criar ou modificar.
	*  Este metodo sobrescreve o da interface Fabricantes.
	*/
	@Override
	public void salvar(Fabricante fabricante) {
		this.sessao.merge(fabricante);
	}

	/** Este metodo Remove um fabricante.
	*  	
	*  @param fabricante, Esta fabricante é o objeto Fabricante que você irá remover.
	*  Este metodo sobrescreve o da interface Fabricantes.
	*/
	@Override
	public void remover(Fabricante fabricante) {
		this.sessao.delete(fabricante);
	}
	
	/** Este metodo pesquisa um fabricante pelo seu nome.
	*  	
	*  @param fabricante, Este fabricante é o nome do fabricante que você está procurando.
	*  @return retorna o fabricante daquele nome que você está pesquisando.
	*   	
	*/
	@Override
	public Fabricante pegaCodigo(String fabricante) {
		Criteria c = this.sessao.createCriteria(Fabricante.class);
		c.add(Restrictions.eq("fabricante", fabricante));
		Fabricante results = (Fabricante) c.uniqueResult();
		return results;
	}
}