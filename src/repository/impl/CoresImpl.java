package repository.impl;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Cor;
import repository.Cores;

/** Esta é uma Classe concreta que implementa a Interface Cores,
*   
* @author silas
* @since 15-08-2016
*/

public class CoresImpl implements Cores{
	
	private Session sessao;

	/**
     * Constructor.
     * @param sessao será a sessao que o hibernate cria para conexoes com o banco.
     */
	public CoresImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de cores cadastradas.
	* 	Este metodo sobrescreve o da interface Cores.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Cor> listar() {
		List<Cor> listaCor = new ArrayList<Cor>();
		listaCor = this.sessao.createCriteria(Cor.class).addOrder(Order.asc("cor")).list();
		return listaCor;
	}

	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo é o id da cor que você está procurando.
	*  @return Cor, retorna a Cor daquele id que você está pesquisando.
	*  Este metodo sobrescreve o da interface Cores.	
	*/
	@Override
	public Cor porCodigo(Integer codigo) {
		return (Cor) sessao.get(Cor.class, codigo);
	}

	/** Este metodo cria ou altera uma cor.
	*  	
	*  @param cor, Esta cor é o objeto Cor que você irá criar ou modificar.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void salvar(Cor cor) {
		sessao.merge(cor);
	}

	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor é o objeto cor que você irá remover.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void remover(Cor cor) {
		this.sessao.delete(cor);
	}
	
	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor é o nome do corque você está procurando.
	*  @return retorna o cor daquele nome que você está pesquisando.
	*   	
	*/
	@Override
	public Cor pegaCodigo(String cor) {
		Criteria c = this.sessao.createCriteria(Cor.class);
		c.add(Restrictions.eq("cor", cor));
		Cor results = (Cor) c.uniqueResult();
		return results;
	}
}