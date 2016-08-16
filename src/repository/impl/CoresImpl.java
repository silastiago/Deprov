package repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import model.Cor;
import repository.Cores;

/** Esta � uma Classe concreta que implementa a Interface Cores,
*   
* @author silas
* @since 15-08-2016
*/

public class CoresImpl implements Cores{
	
	private Session sessao;

	 /**
     * Constructor.
     * @param sessao ser� a sessao que o hibernate cria.
     */
	
	public CoresImpl(Session sessao){
		this.sessao = sessao;
	}
	
	/** Este metodo lista todas as cores cadastradas.
	* 	@return retorna a lista de cores cadastradas.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Cor> listar() {
		List<Cor> listaCor = new ArrayList<Cor>();
		listaCor = this.sessao.createCriteria(Cor.class).list();
		return listaCor;
	}

	/** Este metodo pesquisa uma cor por seu id.
	*  	
	*  @param codigo, Este codigo � o id da cor que voc� est� procurando.
	*  @return Cor, retorna a Cor daquele id que voc� est� pesquisando.
	*   	
	*/
	
	@Override
	public Cor porCodigo(Integer codigo) {
		return (Cor) sessao.get(Cor.class, codigo);
	}

	/** Este metodo salva ou altera uma cor.
	*  	
	*  @param cor, Esta cor � o objeto cor que voc� ir� criar ou modificar.
	*   	
	*/
	@Override
	public void salvar(Cor cor) {
		sessao.merge(cor);
	}

	/** Este metodo Remove uma cor.
	*  	
	*  @param cor, Esta cor � o objeto cor que voc� ir� remover.
	*   	
	*/
	@Override
	public void remover(Cor cor) {
		this.sessao.delete(cor);
	}
}