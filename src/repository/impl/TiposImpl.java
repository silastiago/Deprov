package repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;

import model.Tipo;
import repository.Tipos;


/** Esta � uma Classe concreta que implementa a Interface Tipos,
*   
* @author silas
* @since 16-08-2016
*/
public class TiposImpl implements Tipos{
	private Session sessao;

	/**
     * Constructor.
     * @param sessao ser� a sessao que o hibernate cria para conexoes com o banco.
     */
	public TiposImpl(Session sessao){
		this.sessao = sessao;
	}

	/** Este metodo lista os tipos de veiculos cadastrados.
	* 	@return retorna a lista dos tipos de veiculos cadastrados.
	* 	Este metodo sobrescreve o da interface Tipos.
	*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Tipo> listar() {
		return sessao.createCriteria(Tipo.class).addOrder(Order.asc("tipo")).list();
	}

	/** Este metodo pesquisa um tipo de veiculo por seu id.
	*  	
	*  @param codigo, Este codigo � o id do tipo que voc� est� procurando.
	*  @return retorna o Tipo daquele id que voc� est� pesquisando.
	*  Este metodo sobrescreve o da interface Tipos.	
	*/
	@Override
	public Tipo porCodigo(Integer codigo) {
		return (Tipo) sessao.get(Tipo.class, codigo);
	}

	/** Este metodo cria ou altera um tipo.
	*  	
	*  @param tipo, Este tipo � o objeto Tipo que voc� ir� criar ou modificar.
	*  Este metodo sobrescreve o da interface Tipos.
	*/
	@Override
	public void salvar(Tipo tipo) {
		this.sessao.merge(tipo);
	}

	/** Este metodo Remove um tipo.
	*  	
	*  @param tipo, Esta tipo � o objeto Tipo que voc� ir� remover.
	*  Este metodo sobrescreve o da interface Tipos.
	*/
	@Override
	public void remover(Tipo tipo) {
		this.sessao.delete(tipo);
	}
}