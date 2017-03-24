package repository.impl;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import model.Cor;
import model.Situacao;
import repository.Cores;
import repository.ISituacao;

/** Esta � uma Classe concreta que implementa a Interface Cores,
*   
* @author silas
* @since 15-08-2016
*/

public class SituacaoImpl implements ISituacao{
	
	private Session sessao;

	/**
     * Constructor.
     * @param sessao ser� a sessao que o hibernate cria para conexoes com o banco.
     */
	public SituacaoImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de cores cadastradas.
	* 	Este metodo sobrescreve o da interface Cores.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Situacao> listar() {
		List<Situacao> listaSituacao = new ArrayList<Situacao>();
		listaSituacao = this.sessao.createCriteria(Situacao.class).addOrder(Order.asc("situacao")).list();
		return listaSituacao;
	}

	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo � o id da cor que voc� est� procurando.
	*  @return Cor, retorna a Cor daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Cores.	
	*/
	@Override
	public Situacao porCodigo(Integer codigo) {
		return (Situacao) sessao.get(Situacao.class, codigo);
	}

	/** Este metodo cria ou altera uma cor.
	*  	
	*  @param cor, Esta cor � o objeto Cor que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void salvar(Situacao situacao) {
		sessao.merge(situacao);
	}

	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor � o objeto cor que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void remover(Situacao situacao) {
		this.sessao.delete(situacao);
	}
	
	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor � o nome do corque voc� est� procurando.
	*  @return retorna o cor daquele nome que voc� est� pesquisando.
	*   	
	*/
	@Override
	public Situacao pegaCodigo(String situacao) {
		Criteria c = this.sessao.createCriteria(Situacao.class);
		c.add(Restrictions.eq("situacao", situacao));
		Situacao results = (Situacao) c.uniqueResult();
		return results;
	}
}