package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Fabricante;
import repository.Fabricantes;

/** Esta � uma Classe concreta que implementa a Interface Fabricantes,
*   
* @author silas
* @since 16-08-2016
*/

public class FabricanteImpl implements Fabricantes{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao ser� a sessao que o hibernate cria para conexoes com o banco.
     */
	public FabricanteImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista os fabricantes cadastrados.
	* 	@return retorna a lista dos fabricantes cadastrados.
	* 	Este metodo sobrescreve o da interface Fabricantes.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Fabricante> listar() {
		return sessao.createCriteria(Fabricante.class).addOrder(Order.asc("fabricante")).list();
	}

	/** Este metodo pesquisa um fabricante por seu id.
	*  	
	*  @param codigo, Este codigo � o id do fabricante que voc� est� procurando.
	*  @return retorna o Fabrincante daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Fabricantes.	
	*/
	@Override
	public Fabricante porCodigo(Integer codigo) {
		return (Fabricante) sessao.get(Fabricante.class, codigo);
	}

	/** Este metodo cria ou altera um fabricante.
	*  	
	*  @param fabricante, Este fabricante � o objeto Fabricante que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Fabricantes.
	*/
	@Override
	public void salvar(Fabricante fabricante) {
		this.sessao.merge(fabricante);
	}

	/** Este metodo Remove um fabricante.
	*  	
	*  @param fabricante, Esta fabricante � o objeto Fabricante que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Fabricantes.
	*/
	@Override
	public void remover(Fabricante fabricante) {
		this.sessao.delete(fabricante);
	}
}