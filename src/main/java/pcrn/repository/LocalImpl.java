package repository.impl;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Cor;
import model.Local;
import repository.Cores;
import repository.ILocal;

/** Esta é uma Classe concreta que implementa a Interface Cores,
*   
* @author silas
* @since 15-08-2016
*/

public class LocalImpl implements ILocal{
	
	private Session sessao;

	/**
     * Constructor.
     * @param sessao será a sessao que o hibernate cria para conexoes com o banco.
     */
	public LocalImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de cores cadastradas.
	* 	Este metodo sobrescreve o da interface Cores.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Local> listar() {
		List<Local> listaLocais = new ArrayList<Local>();
		listaLocais = this.sessao.createCriteria(Local.class).addOrder(Order.asc("local")).list();
		return listaLocais;
	}

	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo é o id da cor que você está procurando.
	*  @return Cor, retorna a Cor daquele id que você está pesquisando.
	*  Este metodo sobrescreve o da interface Cores.	
	*/
	@Override
	public Local porCodigo(Integer codigo) {
		return (Local) sessao.get(Local.class, codigo);
	}

	/** Este metodo cria ou altera uma cor.
	*  	
	*  @param cor, Esta cor é o objeto Cor que você irá criar ou modificar.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void salvar(Local local) {
		sessao.merge(local);
	}

	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor é o objeto cor que você irá remover.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void remover(Local local) {
		this.sessao.delete(local);
	}
	
	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor é o nome do corque você está procurando.
	*  @return retorna o cor daquele nome que você está pesquisando.
	*   	
	*/
	@Override
	public Local pegaCodigo(String local) {
		Criteria c = this.sessao.createCriteria(Local.class);
		c.add(Restrictions.eq("local", local));
		Local results = (Local) c.uniqueResult();
		return results;
	}
}