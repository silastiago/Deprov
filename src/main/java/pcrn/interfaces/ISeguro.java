package pcrn.interfaces;

import java.util.List;

import pcrn.model.Seguro;

/** Esta � uma Interface que possui as assinaturas dos metodos da classe Seguro,
*   
* @author silas
* @since 15-08-2016
*/

public interface ISeguro {

	/** Este metodo lista todas as assinaturas dos seguros cadastrados
	*   
	* @return retorna a lista de seguros cadastrados.
	*   	
	*/
	public List<Seguro> listar();
	
	/** Este metodo pesquisa um seguro por seu id.
	*  	
	*  @param codigo, Este codigo � o id do seguro que voc� est� procurando.
	*  @return retorna o Seguro daquele id que voc� est� pesquisando.
	*   	
	*/
	public Seguro porCodigo(Integer codigo);
	
	/** Este metodo cria ou altera um seguro.
	*  	
	*  @param seguro, Esta seguro � o objeto Seguro  que voc� ir� criar ou modificar.
	*   	
	*/
	public void salvar(Seguro seguro);
	
	/** Este metodo Remove um seguro.
	*  	
	*  @param seguro, Esta seguro � o objeto Seguro que voc� ir� remover.
	*   	
	*/
	public void remover(Seguro seguro);
	
	/** Este metodo pesquisa um seguro pelo seu nome.
	*  	
	*  @param seguro, Este seguro � o nome do seguro que voc� est� procurando.
	*  @return retorna o seguro daquele nome que voc� est� pesquisando.
	*   	
	*/
	public Seguro pegaCodigo(String seguro);
}