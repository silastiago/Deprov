package repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Cor;
import model.Pericia;
import repository.Pericias;

/** Esta é uma Classe concreta que implementa a Interface Pericias,
*   
* @author silas
* @since 16-08-2016
*/
public class PericiasImpl implements Pericias{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao será a sessao que o hibernate cria para conexoes com o banco.
     */
	public PericiasImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista os exames veiculares cadastrados.
	* 	@return retorna a lista dos exames veiculares cadastrados.
	* 	Este metodo sobrescreve o da interface Pericia.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Pericia> listar() {
		return sessao.createCriteria(Pericia.class).addOrder(Order.asc("pericia")).list();
	}

	/** Este metodo pesquisa um exame veicular por seu id.
	*  	
	*  @param codigo, Este codigo é o id do exame veicular que você está procurando.
	*  @return retorna o Pericia daquele id que você está pesquisando.
	*  Este metodo sobrescreve o da interface Pericias.	
	*/
	@Override
	public Pericia porCodigo(Integer codigo) {
		return (Pericia) sessao.get(Pericia.class, codigo);
	}

	/** Este metodo cria ou altera uma pericia.
	*  	
	*  @param pericia, Este pericia é o objeto Pericia que você irá criar ou modificar.
	*  Este metodo sobrescreve o da interface Pericias.
	*/
	@Override
	public void salvar(Pericia pericia) {
		this.sessao.merge(pericia);
	}

	/** Este metodo Remove uma pericia.
	*  	
	*  @param pericia, Esta pericia é o objeto Pericia que você irá remover.
	*  Este metodo sobrescreve o da interface Pericias.
	*/
	@Override
	public void remover(Pericia pericia) {
		this.sessao.delete(pericia);
	}

	@Override
	public Pericia pegaCodigo(String pericia) {
		Criteria c = this.sessao.createCriteria(Pericia.class);
		c.add(Restrictions.eq("pericia", pericia));
		Pericia results =  (Pericia) c.uniqueResult();
		return results;
	}
}