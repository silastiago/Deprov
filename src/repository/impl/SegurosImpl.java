package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import model.Seguro;
import repository.Seguros;


/** Esta é uma Classe concreta que implementa a Interface Seguros,
*   
* @author silas
* @since 16-08-2016
*/
public class SegurosImpl implements Seguros{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao será a sessao que o hibernate cria para conexoes com o banco.
     */
	public SegurosImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista os seguros cadastrados.
	* 	@return retorna a lista dos seguros cadastrados.
	* 	Este metodo sobrescreve o da interface Seguros.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Seguro> listar() {
		return sessao.createCriteria(Seguro.class).addOrder(Order.asc("seguro")).list();
	}

	/** Este metodo pesquisa um seguro por seu id.
	*  	
	*  @param codigo, Este codigo é o id do seguro que você está procurando.
	*  @return retorna o Seguro daquele id que você está pesquisando.
	*  Este metodo sobrescreve o da interface Seguros.	
	*/
	@Override
	public Seguro porCodigo(Integer codigo) {
		return (Seguro) sessao.get(Seguro.class, codigo);
	}

	/** Este metodo cria ou altera um seguro.
	*  	
	*  @param seguro, Este seguro é o objeto Seguro que você irá criar ou modificar.
	*  Este metodo sobrescreve o da interface Seguros.
	*/
	@Override
	public void salvar(Seguro seguro) {
		this.sessao.merge(seguro);
	}

	/** Este metodo Remove um seguro.
	*  	
	*  @param seguro, Esta seguro é o objeto Seguro que você irá remover.
	*  Este metodo sobrescreve o da interface Seguros.
	*/
	@Override
	public void remover(Seguro seguro) {
		this.sessao.delete(seguro);
	}
}