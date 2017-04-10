package pcrn.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import pcrn.interfaces.IPessoa;
import pcrn.model.Pessoa;


/** Esta � uma Classe concreta que implementa a Interface Pessoas,
*   
* @author silas
* @since 16-08-2016
*/
public class Pessoas implements IPessoa, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	

	/** Este metodo lista as pessoas cadastradas.
	* 	@return retorna a lista das pessoas cadastradas.
	* 	Este metodo sobrescreve o da interface Pessoas.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Pessoa> listar() {
		
		List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
		Query query = manager.createQuery("Select p from Pessoa p order by login asc");
		listaPessoas = query.getResultList();
		
		return listaPessoas;
	}

	/** Este metodo pesquisa uma pessoa por seu id.
	*  	
	*  @param codigo, Este codigo � o id da pessoa que voc� est� procurando.
	*  @return retorna a Pessoa daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Pessoas.	
	*/
	@Override
	public Pessoa porCodigo(Integer codigo) {
		return manager.find(Pessoa.class, codigo);
	}

	/** Este metodo cria ou altera uma pessoa.
	*  	
	*  @param pessoa, Esta pessoa � o objeto Pessoa que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Pessoas.
	*/
	@Override
	public void salvar(Pessoa pessoa) {
		manager.merge(pessoa);
	}

	/** Este metodo Remove uma pessoa.
	*  	
	*  @param pessoa, Esta pessoa � o objeto Pesssoa que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Pessoas.
	*/
	@Override
	public void remover(Pessoa pessoa) {
		Pessoa pessoaTemporaria = manager.find(Pessoa.class, pessoa.getCodigo());
		manager.remove(pessoaTemporaria);
	}	
	
	@Override
	public Pessoa porLogin(String login) {
		
		Query query = manager.createQuery("from Pessoa where login = :login");
		query.setParameter("login", login);
		Pessoa pessoa = (Pessoa) query.getSingleResult();
		
		return pessoa;
	}
	
}