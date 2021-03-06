package repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


import model.Modelo;
import repository.Modelos;

/** Esta � uma Classe concreta que implementa a Interface Modelos,
*   
* @author silas
* @since 16-08-2016
*/
public class ModelosImpl implements Modelos{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao ser� a sessao que o hibernate cria para conexoes com o banco.
     */
	public ModelosImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista os modelos cadastrados.
	* 	@return retorna a lista dos modelos cadastrados em ordem ascendente.
	* 	Este metodo sobrescreve o da interface Modelos.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Modelo> listar(){
		return sessao.createCriteria(Modelo.class).addOrder(Order.asc("modelo")).list();
	}

	/** Este metodo pesquisa um modelo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do modelo que voc� est� procurando.
	*  @return retorna o Modelo daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Modelos.	
	*/
	@Override
	public Modelo porCodigo(Integer codigo) {
		return (Modelo) sessao.get(Modelo.class, codigo);
	}

	/** Este metodo cria ou altera um modelo.
	*  	
	*  @param modelo, Este modelo � o objeto Modelo que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Modelos.
	*/
	@Override
	public void salvar(Modelo modelo) {
		this.sessao.merge(modelo);
	}

	/** Este metodo Remove um modelo.
	*  	
	*  @param modelo, Esta modelo � o objeto Modelo que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Modelos.
	*/
	@Override
	public void remover(Modelo modelo) {
		this.sessao.delete(modelo);
	}
	
	/** Este metodo pesquisa um modelo pelo seu nome.
	*  	
	*  @param modelo, Este modelo � o nome do modelo que voc� est� procurando.
	*  @return retorna o modelo daquele nome que voc� est� pesquisando.
	*   	
	*/
	@Override
	public Modelo pegaCodigo(String modelo) {
		Criteria c = this.sessao.createCriteria(Modelo.class);
		c.add(Restrictions.eq("modelo", modelo));
		Modelo results = (Modelo) c.uniqueResult();
		return results;
	}
	
	/** Este metodo lista todos os modelos de um determinado fabricante.
	*  	
	*  @param fabricante, Este fabricante � o id do fabricante que voc� est� pesquisando.
	*  @return retorna uma lista de modelos daquele fabricante que voc� est� pesquisando.
	*   	
	*/
	@Override
	public List<Modelo> pegaModelos(String modelo) {
		Criteria c = this.sessao.createCriteria(Modelo.class);
		c.add(Restrictions.eq("modelo.codigo_fabricante", modelo));
		List<Modelo> results = c.list();
		return results;
	}
}