package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.ISituacao;
import pcrn.model.Situacao;

/** Esta � uma Classe concreta que implementa a Interface Cores,
*   
* @author silas
* @since 15-08-2016
*/

public class Situacoes implements ISituacao, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de cores cadastradas.
	* 	Este metodo sobrescreve o da interface Cores.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Situacao> listar() {
		
		List<Situacao> listaSituacoes = new ArrayList<Situacao>();
		Query query = manager.createQuery("Select s from Situacao s order by situacao asc");
		listaSituacoes = query.getResultList();
		
		return listaSituacoes;
	}

	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo � o id da cor que voc� est� procurando.
	*  @return Cor, retorna a Cor daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Cores.	
	*/
	@Override
	public Situacao porCodigo(Integer codigo) {
		return manager.find(Situacao.class, codigo);
	}

	/** Este metodo cria ou altera uma cor.
	*  	
	*  @param cor, Esta cor � o objeto Cor que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void salvar(Situacao situacao) {
		manager.merge(situacao);
	}

	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor � o objeto cor que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Cores.
	*/
	@Override
	public void remover(Situacao situacao) {
		Situacao situacaotemporaria = manager.find(Situacao.class, situacao.getCodigo());
		manager.remove(situacaotemporaria);
	}
	
	/** Este metodo pesquisa uma cor pelo seu nome.
	*  	
	*  @param cor, Este cor � o nome do corque voc� est� procurando.
	*  @return retorna o cor daquele nome que voc� est� pesquisando.
	*   	
	*/
	@Override
	public Situacao pegaCodigo(String situacaoBusca) {
		
		Query query = manager.createQuery("from Situacao where situacao = :situacao");
		query.setParameter("situacao", situacaoBusca);
		Situacao situacao = (Situacao) query.getSingleResult();
		
		return situacao;
	}
}